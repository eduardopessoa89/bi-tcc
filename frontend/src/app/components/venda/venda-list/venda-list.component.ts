import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VendaURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-venda-list',
  templateUrl: './venda-list.component.html',
  styleUrls: ['./venda-list.component.css']
})
export class VendaListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(VendaURL.BASE, VendaURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}