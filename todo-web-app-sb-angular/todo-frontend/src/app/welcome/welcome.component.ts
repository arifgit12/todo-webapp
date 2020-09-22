import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  name: string;
  welcomeMessageFromService:string;

  constructor(
    private route:ActivatedRoute,
    private welcomeSerice: WelcomeDataService) { }

  ngOnInit() {
    this.name = this.route.snapshot.params['name'];
    this.getWelcomeMessage();
  }

  getWelcomeMessage() {
    this.welcomeSerice.executeHelloWorldBeanService().subscribe(
      (response) => {
        this.welcomeMessageFromService = response.message;
      },
      (err) => {
        this.welcomeMessageFromService = err.error.message
      }
    );
  }

  getWelcomeMessageWithParameter() {
    this.welcomeSerice.executeHelloWorldServiceWithPathVariable(this.name).subscribe(
      (response) => {
        this.welcomeMessageFromService = response.message;
      },
      (err) => {
        this.welcomeMessageFromService = err.error.message
      }
    );
  }
}
