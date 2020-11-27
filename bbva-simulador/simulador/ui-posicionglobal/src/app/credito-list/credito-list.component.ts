import {Component, OnInit} from '@angular/core';
import { Credito, CreditoService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'credito-list',
  templateUrl: './credito-list.component.html',
  styles: []
})
export class CreditoListComponent implements OnInit {

  dataSource: MatTableDataSource < Credito > ;
  displayedColumns: string[] = ['iban', 'importe', 'interes', 'plazo', 'tipo'];

  constructor(private creditoService: CreditoService) {
    this.dataSource = new MatTableDataSource<Credito>();
    creditoService.emitter.subscribe(credito => {
      const data = this.dataSource.data;
      data.unshift(credito);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.creditoService.apiCreditosGet().subscribe(creditos => {
      this.dataSource.data = creditos;
    });
  }
}
