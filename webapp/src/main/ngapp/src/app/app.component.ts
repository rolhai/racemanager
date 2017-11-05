import { Component } from '@angular/core';

export class Driver {
    id: number;
    firstname: string;
    lastname: string;
}

const DRIVERS: Driver[] = [
    { id: 11, firstname: 'Fernando',  lastname: 'Alonso' },
    { id: 12, firstname: 'Sebastian', lastname: 'Vettel' },
    { id: 13, firstname: 'Lewis',     lastname: 'Hamilton' }
];

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'Tour of Drivers';
    drivers = DRIVERS;
    selectedDriver: Driver;

    onSelect(driver: Driver): void {
        this.selectedDriver = driver;
    }
}




