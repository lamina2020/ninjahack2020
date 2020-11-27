import {Component, OnInit} from '@angular/core';
import { valores, valoresService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'valores-list',
  templateUrl: './valores-list.component.html',
  styles: []
})
export class valoresListComponent implements OnInit {

  dataSource: MatTableDataSource < valores > ;
  displayedColumns: string[] = ['iban', 'riesgo', 'interes', 'importe'];

  constructor(private valoresService: valoresService) {
    this.dataSource = new MatTableDataSource<valores>();
    valoresService.emitter.subscribe(valores => {
      const data = this.dataSource.data;
      data.unshift(valores);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.valoresService.apivaloressGet().subscribe(valoress => {
      this.dataSource.data = valoress;
    });
  }
}
