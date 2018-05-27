import { NgModule }                 from '@angular/core';
import { RouterModule, Routes }     from '@angular/router';

/*
import { LayoutComponent}           from '../layout/layout.component';
import { DriversComponent }         from '../views/drivers/drivers.component';
import { DriverDetailComponent }    from '../views/driver-detail/driver-detail.component';
import { DashboardComponent }       from '../views/dashboard/dashboard.component';
*/

const routes: Routes = [/*{
    path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'dashboard' },
            { path: 'dashboard', component: DashboardComponent },
            { path: 'drivers', component: DriversComponent  },
            { path: 'driver/:id', component: DriverDetailComponent }*/
        //]
    /*
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard',    component: DashboardComponent },
    { path: 'drivers',      component: DriversComponent },
    { path: 'driver/:id',   component: DriverDetailComponent }
    */
    //}
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class LayoutRoutingModule {}