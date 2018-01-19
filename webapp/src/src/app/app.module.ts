import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { DriversComponent} from './drivers/drivers.component';
import { DriverDetailComponent } from './driver-detail/driver-detail.component';
import { MessagesComponent } from './messages/messages.component';

import { DriverService } from './services/driver.service';
import { MessageService } from './services/message.service';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        DriversComponent,
        DriverDetailComponent,
        MessagesComponent
    ],
    providers: [DriverService, MessageService],
    bootstrap: [AppComponent]
})
export class AppModule { }
