  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';

  import { isNullOrUndefined } from 'util';
  import { ItemVendaURL  , ProdutoURL  } from 'src/app/shared/url/url.domain';
  import { EditHandler } from '../../../shared/helpers/edit-handler';
  import { EditHandlerCaller } from '../../../shared/helpers/edit-handler-caller';
  
    

  @Component({
    selector: 'app-itemvenda-edit',
    templateUrl: './itemvenda-edit.component.html',
    styleUrls: ['./itemvenda-edit.component.css']
  })
  export class ItemVendaEditComponent implements OnInit {

    editHandler: EditHandler;

     // Produto
produtoLoading = false;
produtoList: any[] = [];

    constructor(
      private activatedRoute: ActivatedRoute  
    ) { 
      this.editHandler = new EditHandler(ItemVendaURL.BASE, ItemVendaURL.BASE, false, this);
    }

    ngOnInit(): void {
      this.editHandler.isEditMode = !isNullOrUndefined(this.getParamId());
      this.initForm();
      this.editHandler.getItem(this.getParamId());
       this.searchProduto('');
    }

    initForm() {
      this.editHandler.form = this.editHandler.getFormBuilder().group({
        id: this.editHandler.getFormBuilder().control(undefined, []),
        quantidade: this.editHandler.getFormBuilder().control(undefined, []),produto: this.editHandler.getFormBuilder().control(undefined, []),
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

     searchProduto(term: any) {
    this.produtoLoading = true;
    this.editHandler.getCrudService().getAll(ProdutoURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
        this.produtoList = res.items;
        this.produtoLoading = false;
    })
}
    
      

  }