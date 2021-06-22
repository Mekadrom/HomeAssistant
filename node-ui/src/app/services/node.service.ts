import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { retry, catchError, shareReplay } from 'rxjs/operators';

import { Node } from '../models/node';
import { UrlProviderService } from './url-provider.service';

@Injectable({
  providedIn: 'root'
})
export class NodeService {
  constructor(private http: HttpClient, private urlProvider: UrlProviderService) { }

  public getNodes(): Observable<Node[]> {
    return this.http.get<Node[]>(this.getNodeSearchUrl())
    .pipe(
      retry(1),
      catchError(this.handleError),
      shareReplay()
    );
  }

  private getNodeSearchUrl(): string {
    return this.urlProvider.getNodeSearchUrl();
  }

  private handleError(error: any): Observable<any> {
    return throwError(error);
  }
}
