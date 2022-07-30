import {Injectable} from '@angular/core';
import CryptoJS from "crypto-js";

@Injectable({
  providedIn: 'root'
})
export class CryptoService {

  constructor() {
    // 参考：https://www.jianshu.com/p/95d8eeb8301f
  }

  encryptWithKey(content: string, key: string): string {
    const ciphertext = CryptoJS.AES.encrypt(content, CryptoJS.enc.Utf8.parse(key), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString();

    return ciphertext;
  }

  encrypt(content: string): string {
    const aseKey = '2022-07-27000000';
    const ciphertext = CryptoJS.AES.encrypt(content, CryptoJS.enc.Utf8.parse(aseKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString();

    console.log("加密前: " + content + " 加密后: " + ciphertext);
    return ciphertext;
  }

  decrypt(content: string): string {
    const aseKey = '2022-07-27000000';
    const decrypt = CryptoJS.AES.decrypt(content, CryptoJS.enc.Utf8.parse(aseKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString(CryptoJS.enc.Utf8);

    console.log('解密前: ' + content + ' 解密后: ' + decrypt);
    return decrypt;
  }

}
