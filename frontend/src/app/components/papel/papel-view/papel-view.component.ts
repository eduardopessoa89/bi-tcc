import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PapelURL } from 'src/app/shared/url/url.domain';
import { ViewHandler } from 'src/app/shared/helpers/view-handler';

@Component({
  selector: 'app-papel-view',
  templateUrl: './papel-view.component.html',
  styleUrls: ['./papel-view.component.css']
})
export class PapelViewComponent implements OnInit {

  viewHandler: ViewHandler;

  constructor(private activatedRoute: ActivatedRoute) {
    this.viewHandler = new ViewHandler(PapelURL.BASE, PapelURL.BASE);
  }

  ngOnInit() {
   this.viewHandler.getItem(this.getParamId());
  }

  getParamId() {
    return this.activatedRoute.snapshot.paramMap.get('id');
  }
}