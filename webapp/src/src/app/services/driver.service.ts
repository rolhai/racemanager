import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Injectable } from '@angular/core';
import { Driver } from '../entity/driver';
import { MessageService } from './message.service';

const DRIVERS: Driver[] = [
    { id: 11, firstname: 'Fernando',  lastname: 'Alonso' },
    { id: 12, firstname: 'Sebastian', lastname: 'Vettel' },
    { id: 13, firstname: 'Lewis',     lastname: 'Hamilton' }
];

@Injectable()
export class DriverService {

    constructor(private messageService: MessageService) { }

    getDrivers(): Observable<Driver[]> {
        console.log("load drivers...");
        this.messageService.add('DriverService: fetched drivers');
        return of(DRIVERS);
    }
}
