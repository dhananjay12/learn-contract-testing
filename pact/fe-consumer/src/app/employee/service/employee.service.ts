import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEmployee } from '../employee.model';

type EntityResponseType = HttpResponse<IEmployee>;
type EntityArrayResponseType = HttpResponse<IEmployee[]>;

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public resourceUrl = '/api/employee';

  constructor(protected http: HttpClient) {}

  findAll(): Observable<EntityArrayResponseType> {
    return this.http
      .get<IEmployee[]>(`${this.resourceUrl}/find`, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmployee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  create(employee: IEmployee): Observable<EntityResponseType> {
    return this.http
      .post<IEmployee>(this.resourceUrl, employee, { observe: 'response' });
  }


}
