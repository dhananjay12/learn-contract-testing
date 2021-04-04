import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmployee } from '../employee.model';

import { EmployeeService } from '../service/employee.service';

@Component({
  selector: 'pact-employee',
  templateUrl: './employee.component.html',
})
export class EmployeeComponent implements OnInit {
  employees: IEmployee[];
  isLoading = false;
  links: { [key: string]: number };
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(protected employeeService: EmployeeService, protected modalService: NgbModal) {
    this.employees = [];
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.isLoading = true;

    this.employeeService
      .findAll()
      .subscribe(
        (res: HttpResponse<IEmployee[]>) => {
          this.isLoading = false;
          if(res.body){
            for (let i = 0; i < res.body.length; i++) {
              this.employees.push(res.body[i]);
            }
          }
        },
        () => {
          this.isLoading = false;
        }
      );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEmployee): number {
    return item.id!;
  }
}
