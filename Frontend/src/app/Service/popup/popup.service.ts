import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from '../../shared/popup/popup.component';

@Injectable({
  providedIn: 'root'
})
export class PopupService {


  
  constructor(private dialog: MatDialog) { }
  openSuccessDialog(title: string, message: string) {
    this.dialog.open(PopupComponent, {
      width:"500px",
      data: {
        icon: 'check_circle', // Material icon name for success
        title: title,
        message: message
      }
    });
  }

  openErrorDialog(title: string, message: string) {
    this.dialog.open(PopupComponent, {
      data: {
        icon: 'error', // Material icon name for error
        title: title,
        message: message
      }
    });
  }

  

  }

