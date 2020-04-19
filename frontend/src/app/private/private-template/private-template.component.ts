import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { AppComponent } from 'src/app/app.component';
import { UserService } from 'src/app/shared/services/user.service';
import { environment } from 'src/environments/environment.prod';
import { UserDTO } from 'src/app/shared/model/user.dto';

@Component({
  selector: 'app-private-template',
  templateUrl: './private-template.component.html',
  styleUrls: ['./private-template.component.css']
})
export class PrivateTemplateComponent implements OnInit {

  user: UserDTO;

  constructor(
    public app: AppComponent,
    private userService: UserService) {}

  ngOnInit() {
    this.user = this.userService.getUserAuth();
    $('#container-main').addClass('container-main');
  }

  clickMenu() {
    $('.menu').toggleClass('clicked');
    $('#nav').toggleClass('show');
  }

  clickMenuClose() {
    $('.menu').removeClass('clicked');
    $('#nav').removeClass('show');
  }

  doLogout() {
    this.userService.doLogout();
  }

  getVersion(): string {
    return environment.version;
  }

}