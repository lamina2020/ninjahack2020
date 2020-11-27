import {Component, OnInit} from '@angular/core';
import { Tarjeta, TarjetaService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'tarjeta-list',
  templateUrl: './tarjeta-list.component.html',
  styles: []
})
export class TarjetaListComponent implements OnInit {

  dataSource: MatTableDataSource < Tarjeta > ;
  displayedColumns: string[] = ['PAN', 'saldo'];

  constructor(private tarjetaService: TarjetaService) {
    this.dataSource = new MatTableDataSource<Tarjeta>();
    tarjetaService.emitter.subscribe(tarjeta => {
      const data = this.dataSource.data;
      data.unshift(tarjeta);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.tarjetaService.apiTarjetasGet().subscribe(tarjetas => {
      this.dataSource.data = tarjetas;
    });
  }
}
