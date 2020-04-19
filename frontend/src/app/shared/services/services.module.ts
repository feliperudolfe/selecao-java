import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from './user.service';
import { HttpClientModule } from '@angular/common/http';
import { MsgService } from './msg.service';
import { ProjectService } from './project.service';
import { RoleService } from './role.service';
import { CollaboratorService } from './collaborator.service';
import { ResultService } from './result.service';
import { ExecutionService } from './execution.service';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    MsgService,
    UserService,
    ProjectService,
    RoleService,
    ResultService,
    ExecutionService,
    CollaboratorService
  ]
})
export class ServicesModule { }