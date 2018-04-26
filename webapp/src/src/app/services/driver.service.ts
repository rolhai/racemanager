import { Injectable }       from '@angular/core';
import { Observable }       from 'rxjs/Observable';
import { of }               from 'rxjs/observable/of';
import { Driver }           from '../entity/driver';
import { MessageService }   from './message.service';

const DRIVERS: Driver[] = [
    { id: 11, firstname: 'Fernando',  lastname: 'Alonso' },
    { id: 12, firstname: 'Sebastian', lastname: 'Vettel' },
    { id: 13, firstname: 'Lewis',     lastname: 'Hamilton' }
];

@Injectable()
export class DriverService {

    constructor(private messageService: MessageService) { }

    getDrivers(): Observable<Driver[]> {
        console.log("fetch drivers...");
        this.messageService.add('DriverService: fetched drivers');
        return of(DRIVERS);
    }

    getDriver(id: number): Observable<Driver> {
        console.log("fetch driver " + `id=${id}`);
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        this.messageService.add(`DriverService: fetched driver id=${id}`);
        return of(DRIVERS.find(driver => driver.id === id));
    }
}
