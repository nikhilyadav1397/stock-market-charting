import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/shared/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private companyService: CompanyService, private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    if(this.username == 'admin' && this.password == 'password'){
      this.router.navigate(['/companies']);
    }
  }

  signup(){
    
  }
}
