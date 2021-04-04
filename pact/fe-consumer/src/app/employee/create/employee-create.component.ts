import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmployee, Employee } from '../employee.model';
import { EmployeeService } from '../service/employee.service';

@Component({
  selector: 'pact-employee-create',
  templateUrl: './employee-create.component.html',
})
export class EmployeeCreateComponent implements OnInit {
  isSaving = false;
  errorMsg = '';

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    title: [null, [Validators.required]],
    email: [null, [Validators.required]]
  });

  constructor(protected employeeService: EmployeeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    console.log("init");
    this.activatedRoute.data.subscribe(({ employee }) => {
      if(employee){
        this.updateForm(employee);
      }
    });
  }

  updateForm(employee: IEmployee): void {
    this.editForm.patchValue({
      id: employee.id,
      name: employee.name,
      title: employee.title,
      email: employee.email,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employee = this.createFromForm()
    if (employee.id !== undefined && employee.id !== null) {
      // Update
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }

  }

  private createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      title: this.editForm.get(['title'])!.value,
      email: this.editForm.get(['email'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>): any {
    result.subscribe(
      () => this.onSaveSuccess(),
      response => this.onSaveError(response)
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(response: any): void {
    this.errorMsg = response.error;
    this.isSaving = false;
  }
}
