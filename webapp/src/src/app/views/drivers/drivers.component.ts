import { Component, OnInit } from '@angular/core';
import { Driver } from '../../entity/driver';
import { DriverService } from '../../services/driver.service';

@Component({
    selector: 'view-drivers',
    templateUrl: './drivers.component.html',
    styleUrls:  ['./drivers.component.css']
})
export class DriversComponent implements OnInit {

    drivers : Driver[];

    constructor(private driverService: DriverService) {}

    ngOnInit() {
        this.getDrivers();
    }

    getDrivers(): void {
        this.driverService.list()
            .subscribe(drivers => this.drivers = drivers);
    }
}