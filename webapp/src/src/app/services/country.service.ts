import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Country } from '../entity/country';

import { ResourceService } from './resource.service';

@Injectable()
export class CountryService extends ResourceService<Country> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'countries');
    }
}