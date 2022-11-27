import {Injectable} from '@angular/core';
import CryptoJS from "crypto-js";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CryptoService {
  encryptionKey = environment.encryptionKey;

  constructor() {

  }

  /**
   * 返回格式：yyyy-MM-dd
   */
  static now(): string {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    if (month < 10) {
      // 1位数月份转2位数
      return year + '-' + '0' + month + '-' + date;
    }
    return year + '-' + month + '-' + date;
  }
}
