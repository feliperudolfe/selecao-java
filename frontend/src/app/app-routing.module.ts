import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PublicTemplateComponent } from './public/public-template/public-template.component';
import { PrivateTemplateComponent } from './private/private-template/private-template.component';
import { LoginComponent } from './public/login/login.component';
import { RegisterUserComponent } from './public/register-user/register-user.component';
import { AuthGuard } from './shared/auth/auth.guard';
import { PublicGuard } from './shared/auth/public.guard';
import { HistoricoPrecosComponent } from './private/historico-precos/historico-precos.component';

const routes: Routes = [
  {
    path: '',
    component: PublicTemplateComponent,
    canActivate: [PublicGuard],
    children: [
      {
        path: '',
        component: LoginComponent
      },
      {
        path: 'register-user',
        component: RegisterUserComponent
      }
    ]
  },
  {
    path: 'private',
    component: PrivateTemplateComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: 'historico-precos',
        pathMatch: 'full'
      },
      {
        path: 'historico-precos',
        component: HistoricoPrecosComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
