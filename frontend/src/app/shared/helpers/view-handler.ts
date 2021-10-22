import { CrudService } from '../services/crud.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AppInjector } from './app.injector';
import { ViewHandlerCaller } from './view-handler-caller';

export class ViewHandler {

    item: any;
    loading: boolean;
    service: CrudService;
    router: Router;
    postGetItem = (item: any) => {};

    constructor(
        private serviceUrl: string,
        private routerUrl: string,
        private callingContext: ViewHandlerCaller = undefined
    ) {
        this.service = AppInjector.get(CrudService);
        this.router = AppInjector.get(Router);
        this.loading = false;
    }

    getServiceURL(): string {
        return this.serviceUrl;
    }

    getRouterURL(): string {
        return this.routerUrl;
    }

    backToList(): void {
        this.router.navigate([this.getRouterURL()]);
    }

    getItem(id: any): void {
        this.loading = true;
        this.service.getOne(this.getServiceURL(), id).subscribe((res => {
            this.loading = false;
            this.item = res;
            if (this.callingContext) {
              this.callingContext.postGetItem();
            }
        }), (err) => {
            this.loading = false;
        });
    }

}
