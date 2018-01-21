import { Component, Input }     from '@angular/core';
import { ActivatedRoute }       from '@angular/router';
import { Location }             from '@angular/common';

import { Driver }               from '../entity/driver';
import { DriverService }        from '../services/driver.service';

@Component({
    selector: 'app-driver-detail',
    templateUrl: './driver-detail.component.html',
    styleUrls:  ['./driver-detail.component.css']
})
export class DriverDetailComponent {

    @Input() driver: Driver;

    constructor(
        private route: ActivatedRoute,
        private driverService: DriverService,
        private location: Location
    ) {}

    ngOnInit(): void {
        this.getDriver();
    }

    getDriver(): void {
        const id = +this.route.snapshot.paramMap.get('id');
        this.driverService.getDriver(id)
          .subscribe(driver => this.driver = driver);
    }

    goBack(): void {
        this.location.back();
    }
}
