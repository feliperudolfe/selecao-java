import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';
import { UserDTO } from 'src/app/shared/model/user.dto';
import { Router } from '@angular/router';
import { MsgService } from 'src/app/shared/services/msg.service';
import { ResponseDTO } from 'src/app/shared/model/response.dto';



@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  form: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    senha: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)]),
    confirmarSenha: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)])
  });

  constructor(private userService: UserService, private router: Router, private msg: MsgService) { }

  ngOnInit() {}

  cadastrar() {

    if (!this.form.valid || !this.isSenhaValida()) {
      return;
    }

    const userDTO: UserDTO = {
      email: this.form.controls.email.value,
      password: this.form.controls.senha.value,
      confirmPassword: this.form.controls.confirmarSenha.value,
    }

    this.userService.register(userDTO)
      .subscribe((result) => {
        const response: ResponseDTO = result;
        if (response && response.messages) {
          this.msg.show(response.messages[0])
            .then(() => {
              this.router.navigate(['']);
            })
        }
      });
  }

  isSenhaValida() {
    return this.form.controls.senha.value === this.form.controls.confirmarSenha.value;
  }

}
