import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http/'
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ServerComponent } from './server/server.component';
import { ServersComponent } from './servers/servers.component';
import { WarningAlertComponent } from './warning-alert/warning-alert.component';
import { SuccessAlertComponent } from './success-alert/success-alert.component';
import { UserComponent } from './user/user.component';
import { EsercizioDirettiveComponent } from './esercizio-direttive/esercizio-direttive.component';
import { PadreComponent } from './bindingComponenti/padre/padre.component';
import { FiglioComponent } from './bindingComponenti/figlio/figlio.component';
import { FiglioListaComponent } from './bindingComponenti/figlio-lista/figlio-lista.component';

@NgModule({
  declarations: [
    AppComponent,
    ServerComponent,
    ServersComponent,
    WarningAlertComponent,
    SuccessAlertComponent,
    UserComponent,
    EsercizioDirettiveComponent,
    PadreComponent,
    FiglioComponent,
    FiglioListaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
