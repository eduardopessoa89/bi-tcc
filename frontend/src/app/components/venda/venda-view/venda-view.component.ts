import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VendaURL } from 'src/app/shared/url/url.domain';
import { ViewHandler } from 'src/app/shared/helpers/view-handler';

@Component({
  selector: 'app-venda-view',
  templateUrl: './venda-view.component.html',
  styleUrls: ['./venda-view.component.css']
})
export class VendaViewComponent implements OnInit {

  viewHandler: ViewHandler;

  constructor(private activatedRoute: ActivatedRoute) {
    this.viewHandler = new ViewHandler(VendaURL.BASE, VendaURL.BASE);
  }

  ngOnInit() {
   this.viewHandler.getItem(this.getParamId());
  }

  getParamId() {
    return this.activatedRoute.snapshot.paramMap.get('id');
  }
}