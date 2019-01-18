import { Component, Input }     from '@angular/core';
import { ActivatedRoute }       from '@angular/router';
import { Location }             from '@angular/common';

import { Observable }           from 'rxjs';

import { DriverService }        from '../../services/driver.service';
import { CountryService }       from '../../services/country.service';
import { Driver }               from '../../entity/driver';
import { Country }              from '../../entity/country';

@Component({
    selector: 'view-driver-detail',
    templateUrl: './driver-detail.component.html',
    styleUrls:  ['./driver-detail.component.css']
})
export class DriverDetailComponent {

    @Input() driver: Driver;

    countries: Country[];

    constructor(
        private route: ActivatedRoute,
        private driverService: DriverService,
        private countryService: CountryService,
        private location: Location
    ) {}

    ngOnInit(): void {
        this.getCountries();
        this.getDriver();
    }

    getDriver(): void {
        const id = +this.route.snapshot.paramMap.get('id');
        this.driverService.read(id)
          .subscribe(driver => this.driver = driver)
        //this.driver.country = this.countries.find(country => country.id === this.driver.id);
    }

    getCountries() : void {
        this.countryService.list()
            .subscribe(countries => this.countries = countries);
    }

    goBack(): void {
        this.location.back();
    }

    save(): void {
        if (this.driver.id == null) {
            this.driverService.create(this.driver)
                .subscribe(() => this.goBack());
        }
        else {
            this.driverService.update(this.driver)
                .subscribe(() => this.goBack());
        }
    }

    add(): void {
        this.driver = new Driver();
    }

    compareCountry(c1: Country, c2: Country): boolean {
        if (c1 === undefined || c2 === undefined) {
            return false;
        }
        if (c1 === null || c2 === null) {
            return false;
        }
        console.log("compare " + c1.id + " with " + c2.id);
        return c1 && c2 ? c1.id === c2.id : c1 === c2;
    }


}
