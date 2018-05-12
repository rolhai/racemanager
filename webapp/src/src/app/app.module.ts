import { BrowserModule }    from '@angular/platform-browser';
import { NgModule }         from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule }      from '@angular/forms';

import { AppRoutingModule } from './/app-routing.module';
import { AppComponent }     from './app.component';

import { DashboardComponent }       from './dashboard/dashboard.component';
import { DriversComponent}          from './drivers/drivers.component';
import { DriverDetailComponent }    from './driver-detail/driver-detail.component';
import { MessagesComponent }        from './messages/messages.component';

import { DriverService }    from './services/driver.service';
import { CountryService }    from './services/country.service';
import { MessageService }   from './services/message.service';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule
    ],
    declarations: [
        AppComponent,
        DashboardComponent,
        DriversComponent,
        DriverDetailComponent,
        MessagesComponent
    ],
    providers: [DriverService, CountryService, MessageService],
    bootstrap: [AppComponent]
})
export class AppModule { }
