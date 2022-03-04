import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, shareReplay } from 'rxjs/operators';

import { Home, Room } from '../models';
import { UrlProviderService } from '../services';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  constructor(private http: HttpClient,
              private urlProviderService: UrlProviderService) { }

  public getRooms(homeSearchCriteria?: Home): Observable<Room[]> {
    return this.http.post<Room[]>(this.urlProviderService.getRoomSearchUrl(), {home: homeSearchCriteria}).pipe(
      retry(1),
      catchError(this.handleError),
      shareReplay()
    );
  }

  private handleError(error: any): Observable<any> {
    return throwError(error);
  }
}
