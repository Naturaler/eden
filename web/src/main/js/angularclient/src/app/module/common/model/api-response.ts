export class ApiResponse<T> {
  code!: number;
  msg!: string;
  data!: T;
}
