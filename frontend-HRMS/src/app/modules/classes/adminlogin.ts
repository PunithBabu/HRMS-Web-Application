import { Injectable } from '@angular/core';
import { DepartmentEmployee } from './DepartmentEmployee';
import { Employee } from './employee-list';

@Injectable({
  providedIn: 'root',
})
export class Admin {
  dept!:DepartmentEmployee
}
