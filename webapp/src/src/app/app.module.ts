import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }      from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent }     from './app.component';

import { HeaderComponent }   from './layout/header/header.component';
import { SidebarComponent }  from './layout/sidebar/sidebar.component';
import { FooterComponent }   from './layout/footer/footer.component';
import { MessagesComponent } from './layout/messages/messages.component';

import { DashboardComponent }    from './views/dashboard/dashboard.component';
import { DriversComponent}       from './views/drivers/drivers.component';
import { DriverDetailComponent } from './views/driver-detail/driver-detail.component';

import { ServicesModule } from './services/services.module';
import { TeamsComponent } from './views/teams/teams.component';
import { TeamDetailComponent } from './views/team-detail/team-detail.component';
import { CountriesComponent } from './views/countries/countries.component';
import { CountryDetailComponent } from './views/country-detail/country-detail.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        ServicesModule
    ],
    declarations: [
        AppComponent,
        DashboardComponent,
        DriversComponent,
        DriverDetailComponent,
        TeamsComponent,
        TeamDetailComponent,
        CountriesComponent,
        CountryDetailComponent,
        HeaderComponent,
        SidebarComponent, 
        FooterComponent,
        MessagesComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
