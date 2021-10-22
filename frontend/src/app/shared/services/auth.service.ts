import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AppInjector } from '../helpers/app.injector';
import { CrudService } from './crud.service';

export const STORAGE_KEY = 'user';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends CrudService {

  public router: Router = AppInjector.get(Router);

  constructor() {
    super();
  }

  /**
     * Gets the user.
     */
  getUser() {
    return JSON.parse(localStorage.getItem(STORAGE_KEY));
  }

  /**
   * Verify if the user is logged.
   *
   * @returns {boolean}
   */
  isLogged(): boolean {
    return this.getUser() !== null;
  }

  /**
   * Get token user.
   *
   * @returns {string}
   */
  protected getToken(type: string): string {
    const user = this.getUser();
    if (user) {
      return this.getUser()[type];
    }
    return null;
  }

  /**
   * Get token access user.
   *
   * @returns {string}
   */
  getAccessToken(): string {
    return this.getToken('token');
  }

  /**
   * Get refresh token user.
   *
   * @returns {string}
   */
  getRefreshToken(): string {
    return this.getToken('refreshToken');
  }

  /**
   * Saves the token of the user.
   *
   * @param token
   */
  saveToken(token: any): void {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(token));
  }

  /**
   * Logout user.
   *
   */
  logout(redirectTo: string = '/login'): void {
    localStorage.removeItem(STORAGE_KEY);
    this.router.navigate([redirectTo]);
  }


}
