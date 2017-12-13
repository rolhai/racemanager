import { Injectable } from '@angular/core';
import { Driver } from './driver';

const DRIVERS: Driver[] = [
    { id: 11, firstname: 'Fernando',  lastname: 'Alonso' },
    { id: 12, firstname: 'Sebastian', lastname: 'Vettel' },
    { id: 13, firstname: 'Lewis',     lastname: 'Hamilton' }
];

@Injectable()
export class DriverService {

    constructor() { }

    getDrivers(): Driver[] {
      return DRIVERS;
    }
}
