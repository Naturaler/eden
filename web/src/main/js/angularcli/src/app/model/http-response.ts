import {ApiResponse} from "./api-response";

export class HttpResponse<T> {
  httpCode!: number;
  httpMsg!: string;
  apiResponse!: ApiResponse<T>;
}
