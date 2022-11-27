import {Injectable} from '@angular/core';
import * as CryptoJS from "crypto-js";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CryptoService {
  encryptionKey = environment.encryptionKey;

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
    const aseKey = this.encryptionKey;
    const ciphertext = CryptoJS.AES.encrypt(content, CryptoJS.enc.Utf8.parse(aseKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString();

    return ciphertext;
  }

  decrypt(content: string): string {
    const aseKey = this.encryptionKey;
    const decrypt = CryptoJS.AES.decrypt(content, CryptoJS.enc.Utf8.parse(aseKey), {
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7
    }).toString(CryptoJS.enc.Utf8);

    return decrypt;
  }

}
