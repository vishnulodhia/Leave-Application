import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './shared/page/login/login.component';

import { AdmindashboardComponent } from './Admin/page/admindashboard/admindashboard.component';
import { ManagerdashboredComponent } from './Manager/page/managerdashbored/managerdashbored.component';
import { EmployeedashboredComponent } from './shared/page/employeedashbored/employeedashbored.component';
import { AdminGuard } from './Guard/admin.guard';
import { ManagerGuard } from './Guard/manager.guard';
import { EmployeeGuard } from './Guard/employee.guard';
import { AdduserComponent } from './Admin/components/adduser/adduser.component';
import { UserdetailsComponent } from './shared/page/userdetails/userdetails.component';
import { ShowuserComponent } from './Admin/components/showuser/showuser.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full',
  },
  {
    path: '',
    component: LoginComponent,
    pathMatch: 'full',
  },
  {
    path: 'AdminDashboard',
    component: AdmindashboardComponent,
    canActivate: [AdminGuard],
    children: [
      {
        path: '',
        component: UserdetailsComponent,
      },
      {
        path: 'Adduser',
        component: AdduserComponent,
      },
      {
        path: 'showuser',
        component: ShowuserComponent,
      }

    ],
  },
  {
    path: 'ManagerDashboard',
    component: ManagerdashboredComponent,
    canActivate: [ManagerGuard],
  },
  {
    path: 'EmployeerDashboard',
    component: EmployeedashboredComponent,
    canActivate: [EmployeeGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
