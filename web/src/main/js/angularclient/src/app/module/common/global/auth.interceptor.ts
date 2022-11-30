import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Router} from "@angular/router";
import {Modal} from "bootstrap";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  loginModal!: Modal;

  constructor(private router: Router) {
    const target = document.getElementById('loginModal');
    if (target != null) {
      this.loginModal = new Modal(target);
    }
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(req).pipe(tap(() => {
      },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401 || err.status === 403 || err.status === 0) {
            this.loginModal.show();
          }
        }
      }, () => {
        this.loginModal.hide();
      }));
  }
}
