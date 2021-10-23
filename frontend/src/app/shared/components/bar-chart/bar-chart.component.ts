import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets } from 'chart.js';
import { CubeService } from '../../services/cube.service';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {

  ready: boolean;
  chartLabels = [""];
  chartData: ChartDataSets[] =[];
  barChartLegend = true;
  chartType = "bar"
  @Input()
  cubeQuery;

  @Input()
  height;

  @Input()
  titleText;

  options = {}

  constructor(private cube: CubeService) {
  }

  ngOnInit() {
    this.cube.cubeRequest(this.cubeQuery).subscribe(value => this.resultChanged(value));
  }

  resultChanged(resultSet) {
    this.commonSetup(resultSet);
    this.setTitle(this.titleText)
    this.ready = true;
  }

  setTitle(text) {
    if (text) {
      this.options = {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        },
        title: {
          display: true,
          text: text
        }
      };
    } else {
      this.options = {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        },
      }
    }
  }

  commonSetup(resultSet) {
    resultSet.series().forEach(({series}) => {
      series.forEach(({value, x}) => {
        this.chartData.push({
          data: [value],
          label: x})
      })
    })
  }
}
