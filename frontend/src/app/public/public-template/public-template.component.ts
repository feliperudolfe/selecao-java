import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-public-template',
  templateUrl: './public-template.component.html',
  styleUrls: ['./public-template.component.css']
})
export class PublicTemplateComponent implements OnInit {

  constructor(private router: Router, public app: AppComponent) {}

  ngOnInit() {
    $('#container-main').removeClass('container-main');
  }

  isLogin() {
    return this.router.url === '/';
  }

}
