import { Component, OnInit } from '@angular/core';
import { Driver } from '../../entity/driver';
import { DriverService } from '../../services/driver.service';

@Component({
    selector: 'view-drivers',
    templateUrl: './drivers.component.html',
    styleUrls:  ['./drivers.component.css']
})
export class DriversComponent implements OnInit {

    title = 'Drivers';
    drivers : Driver[];
    //selectedDriver: Driver;

    constructor(private driverService: DriverService) {}

    ngOnInit() {
        this.getDrivers();
    }

    getDrivers(): void {
        this.driverService.getDrivers()
            .subscribe(drivers => this.drivers = drivers);
    }

    /*
    onSelect(driver: Driver): void {
        this.selectedDriver = driver;
    }
    */

}