import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './shared/component/navbar/navbar.component';

import { LoginComponent } from './shared/page/login/login.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ManagerdashboredComponent } from './Manager/page/managerdashbored/managerdashbored.component';
import { EmployeedashboredComponent } from './shared/page/employeedashbored/employeedashbored.component';
import { AdmindashboardComponent } from './Admin/page/admindashboard/admindashboard.component';
import { AdduserComponent } from './Admin/components/adduser/adduser.component';
import { UserdetailsComponent } from './shared/page/userdetails/userdetails.component';
import { InterceptorService } from './HttpInterceptor/interceptor.service';
import { ShowuserComponent } from './Admin/components/showuser/showuser.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { PopupComponent } from './shared/popup/popup.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    ManagerdashboredComponent,
    EmployeedashboredComponent,
    AdmindashboardComponent,
    AdduserComponent,
    UserdetailsComponent,
    ShowuserComponent,
    PopupComponent,
    
  ],
  imports: [ 
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatDialogModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule

  ],
  providers: [{provide:HTTP_INTERCEPTORS,useClass:InterceptorService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
