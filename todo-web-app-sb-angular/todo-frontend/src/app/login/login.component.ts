import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  errorMessage = "Invalid Credentials";
  invalidLogin = false;

  constructor(private router: Router,
              private jwtAuthenticationService: AuthenticationService,
              private authenticationService: HardcodedAuthenticationService) { }

  ngOnInit() {
    
  }

  handleJWTAuthLogin() {
    this.jwtAuthenticationService.authenticate(this.username, this.password).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['welcome', this.username]);
        this.invalidLogin = false;
      },
      (err) => {
        console.log(err);
        this.invalidLogin = true;
      }
    );
  }
}
