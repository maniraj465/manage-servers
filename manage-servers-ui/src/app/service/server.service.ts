import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscriber, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { CustomResponse } from '../interface/custom-response';
import { Server } from '../interface/server';
import { Status } from '../enum/status.enum';

@Injectable({ providedIn: 'root' })
export class ServerService {

  private readonly apiUrl = 'http://localhost:8080/servers';

  constructor(private http: HttpClient) { }

  // Procedural way
  // getServer(): Observable<CustomResponse> {
  //   return this.http.get<CustomResponse>(`http://localhost:8080/servers/list`);
  // }

  // Reactive way
  servers$ = <Observable<never>>this.http.get<CustomResponse>(`${this.apiUrl}/list`)
    .pipe(
      tap(console.log),
      catchError(this.handlerError)
    );

  save$ = (server: Server) => <Observable<never>>this.http.post<CustomResponse>(`${this.apiUrl}/save`, server)
    .pipe(
      tap(console.log),
      catchError(this.handlerError)
    );

  ping$ = (ipAddress: string) => <Observable<never>>this.http.get<CustomResponse>(`${this.apiUrl}/ping/${ipAddress}`)
    .pipe(
      tap(console.log),
      catchError(this.handlerError)
    );

  delete$ = (serverId: number) => <Observable<never>>this.http.delete<CustomResponse>(`${this.apiUrl}/delete/${serverId}`)
    .pipe(
      tap(console.log),
      catchError(this.handlerError)
    );

  filter$ = (status: string, response: CustomResponse) => <Observable<CustomResponse>>
    new Observable<CustomResponse>(
      Subscriber => {
        console.log(response);
        Subscriber.next(
          status === Status.ALL ? { ...response, message: `Servers filtered by ${status}` } :
            {
              ...response,
              message: response.data.servers
                .filter(server => server.status === status).length > 0 ? `Server filtered by
          ${status === Status.UP ? 'Server' + Status.UP : 'Server' + Status.DOWN} status` :
                `No servers of ${status} found`,
              data: {
                servers: response.data.servers
                  .filter(server => server.status === status)
              }
            }
        )
      }
    )
    .pipe(
      tap(console.log),
      catchError(this.handlerError)
    );

  private handlerError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(() => new Error(`An error occurred - Error Code: ${error.status}`));
  }
}
