import { Component } from '@angular/core';
import { Driver } from './driver';
import { DriverService } from './driver.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'Tour of Drivers';
    drivers : Driver[];
    selectedDriver: Driver;

    constructor(private driverService: DriverService) {
    }

    ngOnInit() {
        this.getDrivers();
    }

    getDrivers(): void {
        this.drivers = this.driverService.getDrivers();
    }

    onSelect(driver: Driver): void {
        this.selectedDriver = driver;
    }
}




