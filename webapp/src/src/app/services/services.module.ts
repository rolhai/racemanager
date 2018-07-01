import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DriverService }  from '../services/driver.service';
import { CountryService } from '../services/country.service';
import { MessageService } from '../services/message.service';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [

  ],
  providers: [DriverService, CountryService, MessageService],
})
export class ServicesModule { }