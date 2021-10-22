  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute } from '@angular/router';

  import { isNullOrUndefined } from 'util';
  import { PapelURL   , PermissaoURL, UsuarioURL  } from 'src/app/shared/url/url.domain';
  import { EditHandler } from '../../../shared/helpers/edit-handler';
  import { EditHandlerCaller } from '../../../shared/helpers/edit-handler-caller';
  
      

  @Component({
    selector: 'app-papel-edit',
    templateUrl: './papel-edit.component.html',
    styleUrls: ['./papel-edit.component.css']
  })
  export class PapelEditComponent implements OnInit {

    editHandler: EditHandler;

      // Permissoes
permissoesLoading = false;
permissoesList: any[] = [];// Usuarios
usuariosLoading = false;
usuariosList: any[] = [];

    constructor(
      private activatedRoute: ActivatedRoute    
    ) { 
      this.editHandler = new EditHandler(PapelURL.BASE, PapelURL.BASE, false, this);
    }

    ngOnInit(): void {
      this.editHandler.isEditMode = !isNullOrUndefined(this.getParamId());
      this.initForm();
      this.editHandler.getItem(this.getParamId());
        this.searchPermissao('');this.searchUsuario('');
    }

    initForm() {
      this.editHandler.form = this.editHandler.getFormBuilder().group({
        id: this.editHandler.getFormBuilder().control(undefined, []),
        nome: this.editHandler.getFormBuilder().control(undefined, []),descricao: this.editHandler.getFormBuilder().control(undefined, []),permissoes: this.editHandler.getFormBuilder().control(undefined, []),usuarios: this.editHandler.getFormBuilder().control(undefined, []),
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

      searchPermissao(term: any) {
    this.permissoesLoading = true;
    this.editHandler.getCrudService().getAll(PermissaoURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
        this.permissoesList = res.items;
        this.permissoesLoading = false;
    })
}searchUsuario(term: any) {
    this.usuariosLoading = true;
    this.editHandler.getCrudService().getAll(UsuarioURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
        this.usuariosList = res.items;
        this.usuariosLoading = false;
    })
}
    
        

  }