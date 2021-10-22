import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProdutoURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-produto-list',
  templateUrl: './produto-list.component.html',
  styleUrls: ['./produto-list.component.css']
})
export class ProdutoListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(ProdutoURL.BASE, ProdutoURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}