import {Component, OnInit} from '@angular/core';
import { Cuenta, CuentaService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'cuenta-list',
  templateUrl: './cuenta-list.component.html',
  styles: []
})
export class CuentaListComponent implements OnInit {

  dataSource: MatTableDataSource < Cuenta > ;
  displayedColumns: string[] = ['IBAN', 'saldo'];

  constructor(private cuentaService: CuentaService) {
    this.dataSource = new MatTableDataSource<Cuenta>();
    cuentaService.emitter.subscribe(cuenta => {
      const data = this.dataSource.data;
      data.unshift(cuenta);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.cuentaService.apiCuentasGet().subscribe(cuentas => {
      this.dataSource.data = cuentas;
    });
  }
}
