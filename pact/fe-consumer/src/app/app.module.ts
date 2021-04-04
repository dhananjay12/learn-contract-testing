import {NgModule, LOCALE_ID} from '@angular/core';
import {registerLocaleData} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import locale from '@angular/common/locales/en';
import {BrowserModule, Title} from '@angular/platform-browser';
import {ServiceWorkerModule} from '@angular/service-worker';
import {FaIconLibrary} from '@fortawesome/angular-fontawesome';
import { fontAwesomeIcons } from './config/font-awesome-icons';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {NgbDateAdapter, NgbDatepickerConfig} from '@ng-bootstrap/ng-bootstrap';
import {EmployeeModule} from "./employee/employee.module";
import {EmployeeComponent} from "./employee/list/employee.component";
import {AppRoutingModule} from './app-routing.module';
import {EmployeeRoutingModule} from './employee/route/employee-routing.module';
import { SharedModule } from './shared/shared.module';
import {AppComponent} from "./app.component";

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    SharedModule,
    AppRoutingModule,
    EmployeeModule,
    // Set this to true to enable service worker (PWA)
    ServiceWorkerModule.register('ngsw-worker.js', {enabled: false}),
    HttpClientModule,
    NgxWebstorageModule.forRoot({prefix: 'pact', separator: '-'}),
  ],
  providers: [
    Title,
    {provide: LOCALE_ID, useValue: 'en'},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(iconLibrary: FaIconLibrary, dpConfig: NgbDatepickerConfig) {
    registerLocaleData(locale);
    iconLibrary.addIcons(...fontAwesomeIcons);
  }
}
