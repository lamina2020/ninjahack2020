import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { MatGridListModule } from '@angular/material/grid-list';

import { CuentaListComponent } from './cuenta-list/cuenta-list.component';
import { CuentaService } from './shared';

import { TarjetaListComponent } from './tarjeta-list/tarjeta-list.component';
import { TarjetaService } from './shared';

import { CreditoListComponent } from './credito-list/credito-list.component';
import { CreditoService } from './shared';

import { ValoresListComponent } from './valores-list/valores-list.component';
import { ValoresService } from './shared';


@NgModule({
  declarations: [
    AppComponent,
    CuentaListComponent,
    TarjetaListComponent,
    CreditoListComponent,
    ValoresListComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatDividerModule,
    MatCardModule,
    MatButtonModule,
    MatGridListModule,
    MatTableModule
  ],
  providers: [
    CuentaService,
    TarjetaService,
    CreditoService,
    ValoresService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
