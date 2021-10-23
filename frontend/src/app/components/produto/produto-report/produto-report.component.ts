import { Component, OnInit } from '@angular/core';
import { ChartDataSets } from 'chart.js';

@Component({
  selector: 'app-produto-report',
  templateUrl: './produto-report.component.html',
  styleUrls: ['./produto-report.component.css']
})
export class ProdutoReportComponent implements OnInit {

  cubeQuery = {
    "measures": [
      "Produto.count"
    ],
    "dimensions": [
      "Produto.categoria"
    ]
  }

  textPieTitle = "Gráfico de Pizza de Produtos por Categorias (%)";
  textBarTitle = "Gráfico de Barras de Produtos por Categorias";

  constructor() {
  }

  ngOnInit() {
  }

}
