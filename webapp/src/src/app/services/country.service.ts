import { Injectable }               from '@angular/core';
import { HttpClient, HttpHeaders }  from '@angular/common/http';

import { Observable ,  of }               from 'rxjs';
import { catchError, map, tap }     from 'rxjs/operators';

import { Country }                  from '../entity/country';
import { MessageService }           from './message.service';
import { serviceUrl, httpOptions }  from './server';

@Injectable()
export class CountryService {

    private countryUrl : string = serviceUrl + "countries";

    constructor(
        private http: HttpClient,
        private messageService: MessageService) {}

    private log(message: string) {
        this.messageService.add('CountryService: ' + message);
    }

    getCountries(): Observable<Country[]> {
        console.log("fetch countries");
        this.log('fetch Countries');

        return this.http.get<Country[]>(this.countryUrl).pipe(
            tap(heroes => this.log(`fetched Countries`)),
            catchError(this.handleError('getCountries', []))
        );
    }

    getCountry(id: number): Observable<Country> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`fetch country id=${id}`);        
        this.log(`fetch country id=${id}`);

        const url = `${this.countryUrl}/${id}`;
        return this.http.get<Country>(url).pipe(
            tap(_ => this.log(`fetched country id=${id}`)),
            catchError(this.handleError<Country>(`getCountry id=${id}`))
        );
    }

    updateCountry (country: Country): Observable<Country> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`fetch country id=${country.id}`);
        this.log(`fetch country id=${country.id}`);

        return this.http.put(this.countryUrl, country, httpOptions).pipe(
          tap(_ => this.log(`updated country id=${country.id}`)),
          catchError(this.handleError<any>('updateCountry'))
        );
      }

    addCountry (country: Country): Observable<Country> {
        return this.http.post<Country>(this.countryUrl, country, httpOptions).pipe(
            tap(_ => this.log(`added Country w/ id=${country.id}`)),
            catchError(this.handleError<Country>('addCountry'))
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