import { HttpClient, HttpHeaders }  from '@angular/common/http';

import { Observable ,  of }               from 'rxjs';
import { catchError, map, tap }     from 'rxjs/operators';

import { serviceUrl, httpOptions }  from './server';

import { Resource } from "../entity/resource";

export class ResourceService<T extends Resource> {

    private resourceUrl : string = serviceUrl + this.endpoint;

    constructor(
        private httpClient: HttpClient,
        private endpoint: string) {}
    
    list (): Observable<T[]> {
        console.log("list " + this.endpoint);

        return this.httpClient.get<T[]>(this.resourceUrl)
            .pipe(
                tap(_ => console.log(`listed ` + this.endpoint)),
                catchError(this.handleError<T[]>('list->'+this.endpoint, []))
            );
    }

    read (id: number): Observable<T> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`read `+this.endpoint+` id=${id}`);        

        return this.httpClient.get<T>(`${this.resourceUrl}/${id}`)
            .pipe(
                tap(_ => console.log(`read `+this.endpoint+` id=${id}`)),
                catchError(this.handleError<T>(`read->`+this.endpoint+`  id=${id}`))
            );
    }

    update (res: T): Observable<T> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`update `+this.endpoint+` id=${res.id}`);

        return this.httpClient.put<T>(this.resourceUrl, res, httpOptions)
            .pipe(
                tap(_ => console.log(`updated `+this.endpoint+` id=${res.id}`)),
                catchError(this.handleError<T>('update->'+this.endpoint))
            );
        }

    create (res: T): Observable<T> {
        // note the backticks ( ` ) that define
        // a JavaScript template literal for embedding the id
        console.log(`create new `+this.endpoint);

        return this.httpClient.post<T>(this.resourceUrl, res, httpOptions)
        .pipe(
            tap(_ => console.log(`created `+this.endpoint+` id=${res.id}`)),
            catchError(this.handleError<T>('create->'+this.endpoint))
        );
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<E> (operation = 'operation', result?: E) {
        return (error: any): Observable<E> => {
        
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
        
            // Let the app keep running by returning an empty result.
            return of(result as E);
        };
    }
}