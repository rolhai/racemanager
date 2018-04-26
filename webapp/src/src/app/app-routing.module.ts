import { NgModule }                 from '@angular/core';
import { RouterModule, Routes }     from '@angular/router';
import { DriversComponent }         from './drivers/drivers.component';
import { DriverDetailComponent }    from './driver-detail/driver-detail.component';
import { DashboardComponent }       from './dashboard/dashboard.component';

const routes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard',    component: DashboardComponent },
    { path: 'drivers',      component: DriversComponent },
    { path: 'driver/:id',   component: DriverDetailComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
