import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemVendaURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-itemvenda-list',
  templateUrl: './itemvenda-list.component.html',
  styleUrls: ['./itemvenda-list.component.css']
})
export class ItemVendaListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(ItemVendaURL.BASE, ItemVendaURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}