import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { EmployeeComponent } from './list/employee.component';
import { EmployeeCreateComponent } from './create/employee-create.component';
import { EmployeeRoutingModule } from './route/employee-routing.module';

@NgModule({
  imports: [SharedModule, EmployeeRoutingModule],
  declarations: [EmployeeComponent, EmployeeCreateComponent]
})
export class EmployeeModule {}
