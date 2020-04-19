import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private translate: TranslateService) {

    translate.setDefaultLang('en');
    const acceptLanguage = localStorage.getItem('Accept-Language');
    const lang = (acceptLanguage) ? acceptLanguage : window.navigator.language;

    if (lang != null) {
      this.setTranslate(lang);
    } else {
      this.setTranslate('en');
    }
  }

  getCurrentLanguage() {
    return this.translate.currentLang;
  }

  setTranslate(lang: string) {
    localStorage.setItem('Accept-Language', lang);
    this.translate.use(lang);
  }

}