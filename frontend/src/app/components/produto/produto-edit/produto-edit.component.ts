  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';

  import { isNullOrUndefined } from 'util';
  import { ProdutoURL      } from 'src/app/shared/url/url.domain';
  import { EditHandler } from '../../../shared/helpers/edit-handler';
  import { EditHandlerCaller } from '../../../shared/helpers/edit-handler-caller';
  
     

  @Component({
    selector: 'app-produto-edit',
    templateUrl: './produto-edit.component.html',
    styleUrls: ['./produto-edit.component.css']
  })
  export class ProdutoEditComponent implements OnInit {

    editHandler: EditHandler;

       

    constructor(
      private activatedRoute: ActivatedRoute   
    ) { 
      this.editHandler = new EditHandler(ProdutoURL.BASE, ProdutoURL.BASE, false, this);
    }

    ngOnInit(): void {
      this.editHandler.isEditMode = !isNullOrUndefined(this.getParamId());
      this.initForm();
      this.editHandler.getItem(this.getParamId());
         
    }

    initForm() {
      this.editHandler.form = this.editHandler.getFormBuilder().group({
        id: this.editHandler.getFormBuilder().control(undefined, []),
        valor: this.editHandler.getFormBuilder().control(undefined, []),categoria: this.editHandler.getFormBuilder().control(undefined, []),nome: this.editHandler.getFormBuilder().control(undefined, []),
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

       
    
       

  }