import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {Router} from "@angular/router";
import {Element} from "@angular/compiler";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {
  }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(req).pipe(tap(() => {
      },
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401 || err.status === 403 || err.status === 0) {
            console.log("捕获了403异常！=====" + err);
            // 展示登录框
            const loginModalButton = document.getElementById("loginModalButton");
            if (loginModalButton != null) {
              loginModalButton.click();
            }
          }
        }
      }));
  }
}
