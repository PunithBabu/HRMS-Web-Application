import { DepartmentList } from './department';

import { Employee } from './employee-list';

export class DepartmentManager {
  fromDate!: Date;

  toDate!: Date;

  department!: DepartmentList;

  employee!: Employee;
}
