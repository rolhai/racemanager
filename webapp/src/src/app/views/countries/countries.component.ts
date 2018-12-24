import { Component, OnInit } from '@angular/core';

import { Country } from '../../entity/country';

import { CountryService } from '../../services/country.service';


@Component({
  selector: 'view-countries',
  templateUrl: './countries.component.html',
  styleUrls: ['./countries.component.css']
})
export class CountriesComponent implements OnInit {

  countries : Country[];

  constructor(private countryService: CountryService) { }

  ngOnInit() {
    this.getCountries();
  }

  getCountries(): void {
    this.countryService.list()
        .subscribe(countries => this.countries = countries);
}
}
