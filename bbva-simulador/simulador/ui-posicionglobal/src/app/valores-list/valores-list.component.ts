import {Component, OnInit} from '@angular/core';
import { Valores, ValoresService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'valores-list',
  templateUrl: './valores-list.component.html',
  styles: []
})
export class ValoresListComponent implements OnInit {

  dataSource: MatTableDataSource < Valores > ;
  displayedColumns: string[] = ['iban', 'riesgo', 'interes', 'importe'];

  constructor(private valoresService: ValoresService) {
    this.dataSource = new MatTableDataSource<Valores>();
    valoresService.emitter.subscribe(valores => {
      const data = this.dataSource.data;
      data.unshift(valores);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.valoresService.apiValoressGet().subscribe(valoress => {
      this.dataSource.data = valoress;
    });
  }
}
