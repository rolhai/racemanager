import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { DriverDetailComponent } from './driver-detail.component';
import { DriverService } from './driver.service';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        DriverDetailComponent
    ],
    providers: [DriverService],
    bootstrap: [AppComponent]
})
export class AppModule { }
