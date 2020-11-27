import {Component, OnInit} from '@angular/core';
import { tarjeta, tarjetaService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'tarjeta-list',
  templateUrl: './tarjeta-list.component.html',
  styles: []
})
export class tarjetaListComponent implements OnInit {

  dataSource: MatTableDataSource < tarjeta > ;
  displayedColumns: string[] = ['IBAN', 'saldo'];

  constructor(private tarjetaService: tarjetaService) {
    this.dataSource = new MatTableDataSource<tarjeta>();
    tarjetaService.emitter.subscribe(tarjeta => {
      const data = this.dataSource.data;
      data.unshift(tarjeta);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.tarjetaService.apitarjetasGet().subscribe(tarjetas => {
      this.dataSource.data = tarjetas;
    });
  }
}
