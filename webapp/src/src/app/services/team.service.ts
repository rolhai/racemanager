import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Team } from '../entity/team';

import { ResourceService } from './resource.service';

@Injectable()
export class TeamService extends ResourceService<Team> {

    constructor(httpClient: HttpClient) {
        super(
            httpClient,
            'teams');
    }
}