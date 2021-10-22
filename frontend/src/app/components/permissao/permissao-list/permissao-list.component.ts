import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PermissaoURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-permissao-list',
  templateUrl: './permissao-list.component.html',
  styleUrls: ['./permissao-list.component.css']
})
export class PermissaoListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(PermissaoURL.BASE, PermissaoURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}