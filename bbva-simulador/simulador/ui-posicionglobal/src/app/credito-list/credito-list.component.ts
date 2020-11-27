import {Component, OnInit} from '@angular/core';
import { credito, creditoService } from '../shared';
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'credito-list',
  templateUrl: './credito-list.component.html',
  styles: []
})
export class creditoListComponent implements OnInit {

  dataSource: MatTableDataSource < credito > ;
  displayedColumns: string[] = ['IBAN', 'saldo'];

  constructor(private creditoService: creditoService) {
    this.dataSource = new MatTableDataSource<credito>();
    creditoService.emitter.subscribe(credito => {
      const data = this.dataSource.data;
      data.unshift(credito);
      this.dataSource.data = data;
    })
  }

  ngOnInit() {
    this.creditoService.apicreditosGet().subscribe(creditos => {
      this.dataSource.data = creditos;
    });
  }
}
