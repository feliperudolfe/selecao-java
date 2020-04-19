import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MsgService } from 'src/app/shared/services/msg.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    senha: new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(20)])
  });

  constructor(
    private userService: UserService,
    private msg: MsgService,
    private router: Router) { }

  ngOnInit() {}

  doLogin() {

    if (!this.form.valid) {
      return;
    }

    const username = this.form.controls.email.value;
    const password = this.form.controls.senha.value;

    this.userService.doLogin(username, password)
      .subscribe((result) => {
        if (result.data) {
          const auth = window.btoa(username + ':' + password);
          localStorage.setItem('token', auth);

          this.userService.getAuth()
            .subscribe((result2) => {
              localStorage.setItem('user', JSON.stringify(result2.data));
              this.router.navigate(['private']);
            });
        }
      },
      (result) => {

        this.msg.show(result.error.messages[0]).then(() => {
          this.router.navigate(['']);
        });
      });
  }

}