import { Injectable }               from '@angular/core';
import { HttpClient, HttpHeaders }  from '@angular/common/http';

import { Observable }               from 'rxjs/Observable';
import { of }                       from 'rxjs/observable/of';
import { catchError, map, tap }     from 'rxjs/operators';

import { Driver }                   from '../entity/driver';
import { MessageService }           from './message.service';
import { serviceUrl, httpOptions }  from './server';


const DRIVERS: Driver[] = [
    { id: 11, firstname: 'Fernando',  lastname: 'Alonso',   country: null},
    { id: 12, firstname: 'Sebastian', lastname: 'Vettel',   country: null},
    { id: 13, firstname: 'Lewis',     lastname: 'Hamilton', country: null}
];

@Injectable()
export class DriverService {

    private driverUrl : string = serviceUrl + "drivers";

    constructor(
        private http: HttpClient,
        private messageService: MessageService) {}

    private log(message: string) {
        this.messageService.add('DriverService: ' + message);
    }

    getDrivers(): Observable<Driver[]> {
        console.log("fetch drivers");
        this.log('fetch drivers');

        return this.http.get<Driver[]>(this.driverUrl).pipe(
            tap(heroes => this.log(`fetched drivers`)),
            catchError(this.handleError('getDrivers', []))
        );
        //return of(DRIVERS);
    }

    getDriver(id: number): Observable<Driver> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`fetch driver id=${id}`);        
        this.log(`fetch driver id=${id}`);

        const url = `${this.driverUrl}/${id}`;
        return this.http.get<Driver>(url).pipe(
            tap(_ => this.log(`fetched driver id=${id}`)),
            catchError(this.handleError<Driver>(`getDriver id=${id}`))
        );
        //return of(DRIVERS.find(driver => driver.id === id));
    }

    updateDriver (driver: Driver): Observable<Driver> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`fetch driver id=${driver.id}`);
        this.log(`fetch driver id=${driver.id}`);

        return this.http.put(this.driverUrl, driver, httpOptions).pipe(
          tap(_ => this.log(`updated driver id=${driver.id}`)),
          catchError(this.handleError<any>('updateDriver'))
        );
      }

    addDriver (driver: Driver): Observable<Driver> {
        return this.http.post<Driver>(this.driverUrl, driver, httpOptions).pipe(
            tap(_ => this.log(`added driver w/ id=${driver.id}`)),
            catchError(this.handleError<Driver>('addDriver'))
        );
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T> (operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
       
          // TODO: send the error to remote logging infrastructure
          console.error(error); // log to console instead
       
          // TODO: better job of transforming error for user consumption
          this.log(`${operation} failed: ${error.message}`);
       
          // Let the app keep running by returning an empty result.
          return of(result as T);
        };
      }
}
