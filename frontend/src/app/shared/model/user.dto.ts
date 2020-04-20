export interface UserDTO {
  id?: number;
  email: string;
  password?: string;
  confirmPassword?: string;
  currentPassword?: string;
}