import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from 'src/app/shared/services/notification.service';
import { UserService } from 'src/app/shared/services/user.service';
import { NgxPermissionsService } from 'ngx-permissions';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    private notification: NotificationService,
    private permissionService: NgxPermissionsService
  ) { }

  ngOnInit() {
    this.initLoginForm();
  }

  onSubmit(): void {
    this.login(this.loginForm.value.email, this.loginForm.value.senha);
  }

  protected initLoginForm(): void {
    if (this.userService.isLogged()) {
      this.navigateToHome();
    }
    this.loginForm = this.formBuilder.group({
      email: this.formBuilder.control(undefined, [Validators.required]),
      senha: this.formBuilder.control(undefined, [Validators.required])
    });
  }

  protected login(email: string, senha: string): void {
    this.loading = !this.loading;
    this.userService.login(email, senha).subscribe((result: any) => {
      this.userService.saveToken(result);
           this.permissionService.loadPermissions(this.userService.getUser() ? this.userService.getUser().permissoes : []);
      this.loading = !this.loading;
      this.navigateToHome();
    }, (error: any) => {
      this.notification.errorMessage('Could not login.')
      this.loading = !this.loading;
    });
  }

  protected navigateToHome(): void {
    this.router.navigate(['/']);
  }

  get isFormValid(): boolean {
      return this.loginForm && this.loginForm.valid;
  }

}
