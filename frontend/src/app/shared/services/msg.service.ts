import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { TranslateService } from '@ngx-translate/core';
import { MessageDTO } from '../model/message.dto';


@Injectable()
export class MsgService {

  constructor(private translate: TranslateService) { }

  Dialog = Swal.mixin({
    showConfirmButton: true,
    allowOutsideClick: true
  })

  ConfirmDialog = Swal.mixin({
    showConfirmButton: true,
    showCancelButton: true,
    allowOutsideClick: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
  })

  Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 5000,
    timerProgressBar: true,
    onOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  })

  toast(type: SweetAlertIcon, text: string) {
    return this.Toast.fire({
      icon: type,
      title: text
    })
  }

  show(msg: MessageDTO) {
    const type = msg.type;
    const msgText = msg.text;
    return this.Dialog.fire({
      icon: type,
      title: this.getTitle(type),
      text: msgText
    })
  }

  showConfirm(msg: MessageDTO) {
    const type = msg.type;
    const msgText = msg.text;
    return this.ConfirmDialog.fire({
      icon: type,
      title: this.getTitle(type),
      text: msgText
    })
  }

  private getTitle(type: SweetAlertIcon): string {

    let title: any = this.translate.get('msg.error');
    if (type === 'success') {
      title = this.translate.get('msg.success');
    } else if (type === 'warning') {
      title = this.translate.get('msg.warning');
    } else if (type === 'info') {
      title = this.translate.get('msg.info');
    } else if (type === 'question') {
      title = this.translate.get('msg.question');
    }

    return title.value;
  }

}