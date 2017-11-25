import { Component, Input } from '@angular/core';
import { Driver } from './driver';

@Component({
    selector: 'driver-detail',
    templateUrl: './driver-detail.component.html',
})
export class DriverDetailComponent {
    @Input() driver: Driver;
}
