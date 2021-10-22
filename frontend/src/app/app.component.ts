import { Component } from '@angular/core';
import { NgxPermissionsService } from 'ngx-permissions';
import { UserService } from './shared/services/user.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BusinessSaleProject';
  
      constructor(private userService: UserService, private permissionService: NgxPermissionsService) {    }
    
    ngOnInit(): void {
        this.loadPermissions();
    }
    
    protected loadPermissions() {
             this.permissionService.loadPermissions(this.userService.getUser() ? this.userService.getUser().permissoes : []);
    }


}

