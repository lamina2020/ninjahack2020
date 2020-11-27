export * from './fight.service';
export * from './cuenta.service';
import { FightService } from './fight.service';
import { CuentaService } from './cuenta.service';
export const APIS = [FightService,CuentaService];
