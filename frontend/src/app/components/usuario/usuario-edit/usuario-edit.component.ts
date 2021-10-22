  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';

  import { isNullOrUndefined } from 'util';
  import { UsuarioURL     , PapelURL  } from 'src/app/shared/url/url.domain';
  import { EditHandler } from '../../../shared/helpers/edit-handler';
  import { EditHandlerCaller } from '../../../shared/helpers/edit-handler-caller';
  
       

  @Component({
    selector: 'app-usuario-edit',
    templateUrl: './usuario-edit.component.html',
    styleUrls: ['./usuario-edit.component.css']
  })
  export class UsuarioEditComponent implements OnInit {

    editHandler: EditHandler;

        // Papeis
papeisLoading = false;
papeisList: any[] = [];

    constructor(
      private activatedRoute: ActivatedRoute     
    ) { 
      this.editHandler = new EditHandler(UsuarioURL.BASE, UsuarioURL.BASE, false, this);
    }

    ngOnInit(): void {
      this.editHandler.isEditMode = !isNullOrUndefined(this.getParamId());
      this.initForm();
      this.editHandler.getItem(this.getParamId());
          this.searchPapel('');
    }

    initForm() {
      this.editHandler.form = this.editHandler.getFormBuilder().group({
        id: this.editHandler.getFormBuilder().control(undefined, []),
        nome: this.editHandler.getFormBuilder().control(undefined, []),sobrenome: this.editHandler.getFormBuilder().control(undefined, []),senha: this.editHandler.getFormBuilder().control(undefined, []),email: this.editHandler.getFormBuilder().control(undefined, []),papeis: this.editHandler.getFormBuilder().control(undefined, []),
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

        searchPapel(term: any) {
    this.papeisLoading = true;
    this.editHandler.getCrudService().getAll(PapelURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
        this.papeisList = res.items;
        this.papeisLoading = false;
    })
}
    
         

  }