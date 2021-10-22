import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(UsuarioURL.BASE, UsuarioURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}