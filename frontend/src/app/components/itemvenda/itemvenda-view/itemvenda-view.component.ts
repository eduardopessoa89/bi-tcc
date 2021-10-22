import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemVendaURL } from 'src/app/shared/url/url.domain';
import { ViewHandler } from 'src/app/shared/helpers/view-handler';

@Component({
  selector: 'app-itemvenda-view',
  templateUrl: './itemvenda-view.component.html',
  styleUrls: ['./itemvenda-view.component.css']
})
export class ItemVendaViewComponent implements OnInit {

  viewHandler: ViewHandler;

  constructor(private activatedRoute: ActivatedRoute) {
    this.viewHandler = new ViewHandler(ItemVendaURL.BASE, ItemVendaURL.BASE);
  }

  ngOnInit() {
   this.viewHandler.getItem(this.getParamId());
  }

  getParamId() {
    return this.activatedRoute.snapshot.paramMap.get('id');
  }
}