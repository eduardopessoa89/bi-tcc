import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PermissaoURL } from 'src/app/shared/url/url.domain';
import { ViewHandler } from 'src/app/shared/helpers/view-handler';

@Component({
  selector: 'app-permissao-view',
  templateUrl: './permissao-view.component.html',
  styleUrls: ['./permissao-view.component.css']
})
export class PermissaoViewComponent implements OnInit {

  viewHandler: ViewHandler;

  constructor(private activatedRoute: ActivatedRoute) {
    this.viewHandler = new ViewHandler(PermissaoURL.BASE, PermissaoURL.BASE);
  }

  ngOnInit() {
   this.viewHandler.getItem(this.getParamId());
  }

  getParamId() {
    return this.activatedRoute.snapshot.paramMap.get('id');
  }
}