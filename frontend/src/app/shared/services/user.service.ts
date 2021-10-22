import { Injectable } from '@angular/core';
import { LoginURL } from '../url/url.domain';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService extends AuthService {

  constructor() {
    super();
  }

  login(email, senha) {
    return this.post(LoginURL.BASE, { email, senha });
  }

}
