import { Component } from '@angular/core';
import { ServerService } from './service/server.service';
import { CustomResponse } from './interface/custom-response';
import { AppState } from './interface/app-state';
import { BehaviorSubject, Observable, catchError, map, of, startWith } from 'rxjs';
import { DataState } from './enum/data-state.enum';
import { ServerType } from './enum/server-type.enum';
import { Status } from './enum/status.enum';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  appState$: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  readonly ServerType = ServerType;
  readonly Status = Status;
  private filterSubject = new BehaviorSubject<string>('');
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  filterStatus$ = this.filterSubject.asObservable();

  constructor(private serverService: ServerService) { }

  ngOnInit(): void {
    this.appState$ = this.serverService.servers$
      .pipe(
        map(
          response => {
            this.dataSubject.next(response);
            return { dataState: DataState.LOADED_STATE, appData: response }
          }
        ),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      );
  }

  pingServer(ipAddress: string): void {
    this.filterSubject.next(ipAddress);
    this.appState$ = this.serverService.ping$(ipAddress)
      .pipe(
        map(response => {
          const index = this.dataSubject.value.data.servers.findIndex(server =>
            server.id === response['data']['server']['id']);
          this.dataSubject.value.data.servers[index] = response['data']['server'];
          this.filterSubject.next('');
          return { dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }
        }),
        startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
        catchError((error: string) => {
          this.filterSubject.next('');
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      );
  }

  filterServers(status: any): void {
    this.appState$ = this.serverService.filter$(status.target.value, this.dataSubject.value)
      .pipe(
        map(response => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }

  deleteServer(ipAddress: string): void {
  //   this.filterSubject.next(ipAddress);
  //   this.appState$ = this.serverService.ping$(ipAddress)
  //     .pipe(
  //       map(response => {
  //         const index = this.dataSubject.value.data.servers.findIndex(server =>
  //           server.id === response['data']['server']['id']);
  //         this.dataSubject.value.data.servers[index] = response['data']['server'];
  //         this.filterSubject.next('');
  //         return { dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }
  //       }),
  //       startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
  //       catchError((error: string) => {
  //         this.filterSubject.next('');
  //         return of({ dataState: DataState.ERROR_STATE, error })
  //       })
  //     );
  }
}
