import { NgModule }                 from '@angular/core';
import { RouterModule, Routes }     from '@angular/router';

//import { LayoutModule} from './layout/layout.module'

import { DashboardComponent }       from './views/dashboard/dashboard.component';

import { DriversComponent }         from './views/drivers/drivers.component';
import { DriverDetailComponent }    from './views/driver-detail/driver-detail.component';

import { TeamsComponent }           from './views/teams/teams.component';
import { TeamDetailComponent }      from './views/team-detail/team-detail.component';

import { CountriesComponent }       from './views/countries/countries.component';
import { CountryDetailComponent }   from './views/country-detail/country-detail.component';

const routes: Routes = [
    /*
    { path: '', loadChildren: './layout/layout.module#LayoutModule' }
    */
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard',     component: DashboardComponent },  
    { path: 'teams',         component: TeamsComponent },
    { path: 'teams/:id',     component: TeamDetailComponent },
    { path: 'countries',     component: CountriesComponent },
    { path: 'countries/:id', component: CountryDetailComponent },
    { path: 'drivers',       component: DriversComponent },
    { path: 'driver/:id',    component: DriverDetailComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
