import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HelloWorldBean {
  constructor(public message:string){ }
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(
    private http:HttpClient
  ) { }

  executeHelloWorldBeanService() {

    let basicAuthHeader = this.createBasicAuthenticationHeader();
    let header = new HttpHeaders(
      {Authorization : basicAuthHeader}
    );

    return this.http.get<HelloWorldBean>('http://localhost:8080/hello-world-bean', {headers : header});
  }

  executeHelloWorldServiceWithPathVariable(name) {

    let basicAuthHeader = this.createBasicAuthenticationHeader();
    let header = new HttpHeaders(
      {Authorization : basicAuthHeader}
    );

    return this.http.get<HelloWorldBean>(
      `http://localhost:8080/hello-world/path-variable/${name}`, {headers : header}
      );
  }

  createBasicAuthenticationHeader() {
    let username = 'admin';
    let password = 'admin';
    let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    return basicAuthHeaderString;
  }
}
