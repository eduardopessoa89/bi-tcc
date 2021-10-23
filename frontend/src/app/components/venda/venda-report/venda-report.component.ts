import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-venda-report',
  templateUrl: './venda-report.component.html',
  styleUrls: ['./venda-report.component.css']
})
export class VendaReportComponent implements OnInit {

  barCubeQuery = {
    "measures": [
      "Venda.count"
    ],
    "dimensions": [
      "Venda.cliente"
    ]
  }

  textTitleBar = "Gráfico de Barra de Vendas por Cliente"
  textTitlePie = "Gráfico de Pizza de Vendas por Cliente (%)"

  constructor() { }

  ngOnInit() {
  }

}
