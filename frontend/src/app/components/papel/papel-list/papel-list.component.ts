import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PapelURL } from 'src/app/shared/url/url.domain';
import { ListHandler } from '../../../shared/helpers/list-handler';

@Component({
  selector: 'app-papel-list',
  templateUrl: './papel-list.component.html',
  styleUrls: ['./papel-list.component.css']
})
export class PapelListComponent implements OnInit {

  listHandler: ListHandler;

  constructor(private router: Router) {
    this.listHandler = new ListHandler(PapelURL.BASE, PapelURL.BASE)
  }

  ngOnInit() {
    this.listHandler.listItems();
  }

}