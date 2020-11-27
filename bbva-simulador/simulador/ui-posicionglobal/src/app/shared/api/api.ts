export * from './cuenta.service';
import { CuentaService } from './cuenta.service';

export * from './tarjeta.service';
import { TarjetaService } from './tarjeta.service';

export * from './credito.service';
import { CreditoService } from './credito.service';

export * from './valores.service';
import { ValoresService } from './valores.service';

export const APIS = [CuentaService,TarjetaService,CreditoService,ValoresService];
