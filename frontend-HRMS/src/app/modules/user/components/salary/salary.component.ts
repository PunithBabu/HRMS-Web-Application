import { Component, OnInit } from '@angular/core';

// import html2canvas from 'html2canvas';

import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';


import { VirtualTimeScheduler } from 'rxjs';

import { Employee } from 'src/app/modules/classes/employee-list';

import { Gender } from 'src/app/modules/classes/gender';

import { Salary } from 'src/app/modules/classes/salary';

import { UserService } from 'src/app/users/user.service';
// import jsPDF from 'jspdf';





@Component({

  selector: 'app-salary',

  templateUrl: './salary.component.html',

  styleUrls: ['./salary.component.css']

})




export class SalaryComponent implements OnInit {




  employee: Employee;

  id!:number;

  salaryby!:Salary[];




  constructor(private salaryService: UserService) {

    const loggedInEmployeeStr = localStorage.getItem('loggedInEmployee');

    this.employee = loggedInEmployeeStr ? JSON.parse(loggedInEmployeeStr)[0] : new Employee();

    const id=this.employee.empNo

  }




  ngOnInit(): void {

      this.getsalarybyempNo();

  }

 

  getsalarybyempNo():void{

    this. salaryService.getSalaryByEmpNo(this.employee.empNo).subscribe((data: Salary[])=>{

      this.salaryby=data;})

  }





  downloadTable(): void {

    const table = document.getElementById('salaryTable');

    if (table instanceof HTMLElement) {

      html2canvas(table).then(canvas => {

      const imgData = canvas.toDataURL('image/png');

      const pdf = new jsPDF();

      const imgProps = pdf.getImageProperties(imgData);

      const pdfWidth = pdf.internal.pageSize.getWidth();

      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;




      pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);

      pdf.save('salary_table.pdf');

    });

  }

}

 
}