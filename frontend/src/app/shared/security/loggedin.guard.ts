import { Injectable } from '@angular/core';
import { CanActivate, CanLoad, Route, UrlSegment, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { CrudService } from '../services/crud.service';
import { UserService } from '../services/user.service';
import { LoginURL } from '../url/url.domain';

@Injectable({
  providedIn: 'root'
})
export class LoggedinGuard implements CanActivate, CanLoad {

  constructor(private crudService: CrudService, private userService: UserService, private router: Router) {}

  async canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {
    return this.handleLogin();
  }
  
  async canLoad(
    route: Route,
    segments: UrlSegment[]) {
    return this.handleLogin();
  }

  async handleLogin() {
    if (this.userService.isLogged()) {
      if (await this.tokenVerify()) {
        return true;
      } else {
        localStorage.removeItem('user');
        this.router.navigate(['/login']);
        return false;
      }
    }
    this.router.navigate(['/login']);
    return false;
  }

  async tokenVerify() {
    const response = await this.crudService.get(LoginURL.VERIFY_TOKEN).toPromise().then(() => {
      return true;
    }).catch(() => {
      return false;
    });
    return response;
  }
}
