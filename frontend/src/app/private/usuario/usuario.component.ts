import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';
import { UserDTO } from 'src/app/shared/model/user.dto';
import { MsgService } from 'src/app/shared/services/msg.service';
import * as $ from 'jquery';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  usuario: UserDTO;

  constructor(
    private msg: MsgService,
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.usuario = this.userService.getUserAuth()
  }

  limparSenha() {
    this.usuario.password = undefined;
    this.usuario.confirmPassword = undefined;
    this.usuario.currentPassword = undefined;
  }

  salvarSenha() {
    this.userService.update(this.usuario)
      .subscribe((response) => {
        this.msg.show(response.messages[0]).then(() => {
          $('#alterar-senha-fechar-btn').click();
          this.userService.doLogout();
          this.router.navigate(['']);
        });
      },
      (response) => {
        this.msg.show(response.error.messages[0]);
      });
  }

}
