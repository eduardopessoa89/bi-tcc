  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';

  import { isNullOrUndefined } from 'util';
  import { VendaURL   , ItemVendaURL  } from 'src/app/shared/url/url.domain';
  import { EditHandler } from '../../../shared/helpers/edit-handler';
  import { EditHandlerCaller } from '../../../shared/helpers/edit-handler-caller';
  
     

  @Component({
    selector: 'app-venda-edit',
    templateUrl: './venda-edit.component.html',
    styleUrls: ['./venda-edit.component.css']
  })
  export class VendaEditComponent implements OnInit {

    editHandler: EditHandler;

      // ItensVenda
itensVendaLoading = false;
itensVendaList: any[] = [];

    constructor(
      private activatedRoute: ActivatedRoute   
    ) { 
      this.editHandler = new EditHandler(VendaURL.BASE, VendaURL.BASE, false, this);
    }

    ngOnInit(): void {
      this.editHandler.isEditMode = !isNullOrUndefined(this.getParamId());
      this.initForm();
      this.editHandler.getItem(this.getParamId());
        this.searchItemVenda('');
    }

    initForm() {
      this.editHandler.form = this.editHandler.getFormBuilder().group({
        id: this.editHandler.getFormBuilder().control(undefined, []),
        dataVenda: this.editHandler.getFormBuilder().control(undefined, []),cliente: this.editHandler.getFormBuilder().control(undefined, []),itensVenda: this.editHandler.getFormBuilder().control(undefined, []),
      });
    }

    getParamId(): string {
      return this.activatedRoute.snapshot.paramMap.get('id');
    }

    preInsert(): void {
    }

    preUpdate() {
    }

    postGetItem() {
        this.editHandler.form.get('datavenda').setValue(new Date(this.editHandler.item.datavenda).toISOString().slice(0, 10));  
    }

    postInsert() {
      this.editHandler.notificationService.successMessage('Item created successfully');
    }

    postUpdate() {
      this.editHandler.notificationService.successMessage('Item updated successfully');
    }
    
    getFormValue(): Object {
      return this.editHandler.form.value;
    }

      searchItemVenda(term: any) {
    this.itensVendaLoading = true;
    this.editHandler.getCrudService().getAll(ItemVendaURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
        this.itensVendaList = res.items;
        this.itensVendaLoading = false;
    })
}
    
       

  }