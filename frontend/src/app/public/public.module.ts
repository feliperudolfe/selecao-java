import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicRoutingModule } from './public-routing.module';
import { LoginComponent } from './login/login.component';
import { PublicTemplateComponent } from './public-template/public-template.component';
import { ServicesModule } from '../shared/services/services.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterUserComponent } from './register-user/register-user.component';
import { TranslateModule } from '@ngx-translate/core';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';


@NgModule({
  declarations: [
    LoginComponent,
    PublicTemplateComponent,
    RegisterUserComponent,
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    ServicesModule,
    ReactiveFormsModule,
    TranslateModule,
    SweetAlert2Module
  ],
  providers: []
})
export class PublicModule { }
