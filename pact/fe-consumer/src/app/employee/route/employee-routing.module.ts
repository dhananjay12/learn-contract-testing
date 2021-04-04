import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EmployeeComponent } from '../list/employee.component';
import { EmployeeCreateComponent } from '../create/employee-create.component';
import { EmployeeRoutingResolveService } from './employee-routing-resolve.service';

const employeeRoute: Routes = [
  {
    path: '',
    component: EmployeeComponent,
  },
  {
    path: 'new',
    component: EmployeeCreateComponent,
    resolve: {
      employee: EmployeeRoutingResolveService,
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(employeeRoute)],
  exports: [RouterModule],
})
export class EmployeeRoutingModule {}
