import { Component, OnInit } from '@angular/core';
import { EditHandler } from 'src/app/shared/helpers/edit-handler';
import { PapelPermissaoURL, PapelURL, PermissaoURL } from 'src/app/shared/url/url.domain';

@Component({
  selector: 'app-papel-permissao',
  templateUrl: './papel-permissao.component.html',
  styleUrls: ['./papel-permissao.component.css']
})
export class PapelPermissaoComponent implements OnInit {

  editHandler: EditHandler;

  // Permissoes
  permissoesLoading = false;
  permissoesList: any[] = [];

  // Authors
  papeisLoading = false;
  papeisList: any[] = [];

  papelPermissaoList: any[] = [];

  constructor() {
    this.editHandler = new EditHandler(PapelPermissaoURL.MULTIPLE, PapelURL.BASE, true, this);
  }

  ngOnInit() {
    this.initForm();
    this.searchPapeis('');
    this.searchPermissoes('');
  }

  initForm() {
    this.editHandler.form = this.editHandler.getFormBuilder().group({
      id: this.editHandler.getFormBuilder().control(undefined, []),
      papeis: this.editHandler.getFormBuilder().control(undefined, []), 
      permissoes: this.editHandler.getFormBuilder().control(undefined, []),
    });
  }

  preInsert(): void {
    const papeis = this.editHandler.form.get('papeis').value;
    const permissoes = this.editHandler.form.get('permissoes').value;
    papeis.forEach(papel => {
      permissoes.forEach(permissao => {
        const papelPermissao = { papel, permissao };
        this.papelPermissaoList.push(papelPermissao);
      });
    });
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

  searchPermissoes(term: any) {
    this.permissoesLoading = true;
    this.editHandler.getCrudService().getAll(PermissaoURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
      this.permissoesList = res.items;
      this.permissoesLoading = false;
    })
  }

  searchPapeis(term: any) {
    this.papeisLoading = true;
    this.editHandler.getCrudService().getAll(PapelURL.BASE, this.editHandler.generateFilter(term)).subscribe((res: any) => {
      this.papeisList = res.items;
      this.papeisLoading = false;
    })
  }

  getFormValue(): Object { 
    return this.papelPermissaoList;
  }

}
