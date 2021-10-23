import {Component, Input, OnInit} from '@angular/core';
import {ResultSet} from '@cubejs-client/core';
import {CubeService} from '../../services/cube.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {

  ready: boolean;
  chartLabels = [];
  chartData = [];
  chartType = "pie"
  pieChartLegend = true;
  options = {}

  @Input()
  cubeQuery;

  @Input()
  height;

  @Input()
  titleText;

  constructor(private cube: CubeService) {
  }

  ngOnInit() {
    this.cube.cubeRequest(this.cubeQuery).subscribe(value => this.resultChanged(value));
  }

  setTitle(text) {
    if (text) {
      this.options = {
        title: {
          display: true,
          text: text
        }
      }
    } else {
      this.options = {}
    }

  }

  resultChanged(resultSet: ResultSet<any>) {
    this.commonSetup(resultSet);
    this.setTitle(this.titleText);
    this.ready = true;
  }

  commonSetup(resultSet: ResultSet<any>) {
    this.chartLabels = resultSet.chartPivot().map(result => result.x);
    resultSet.series().forEach(({series}) => {
      let sum = 0;
      series.forEach(({value}) => sum += value);
      series.forEach(({value}) => {
        this.chartData.push(Math.round(value / sum * 100))
      })
    })
  }
}
