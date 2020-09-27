import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor {

  constructor(
    private authenticationService: AuthenticationService
  ) {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let basicAuthHeaderToken = this.authenticationService.getAuthenticatedToken();
    let username = this.authenticationService.getAuthenticatedUser();

    if(basicAuthHeaderToken && username) {
      request = request.clone({
        setHeaders : {
          Authorization : basicAuthHeaderToken
        }
      });
    }

    return next.handle(request);
  }
}