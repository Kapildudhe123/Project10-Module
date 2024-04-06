import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carlist',
  templateUrl: './carlist.component.html',
  styleUrls: ['./carlist.component.css']
})
export class CarlistComponent {
  form: any = {

    list: [],
    search: {},
    delete: {},
    pageNo: 0,
    message: "",
    preload: []
  }



  constructor(private http: HttpClient, private router: Router) { }
  ngOnInit(): void {
    this.preload();
    this.search();
  
   
  }
  previous() {
    this.form.pageNo--;
    this.search();

  }
  next() {
    this.form.pageNo++;
    this.search();
  }


  search() {

    var self = this;

    this.http.post('http://localhost:8080/car/search/' + self.form.pageNo, this.form.search).subscribe((res: any) => {

      if (res.result.message) {
        self.form.message = res.result.message;
      }
      self.form.list = res.result.data;



    })

  }
  edit(page: any) {
    this.router.navigateByUrl(page)

  }
  onCheckboxChange(Id: number) {
   
    this.form.delete.id = Id;
  }

  delete() {
    var self = this;
    this.http.get('http://localhost:8080/car/delete/' + this.form.delete.id).subscribe((res: any) => {
      self.form.message = res.result.message;
      console.log('message => ', self.form.message)

      self.search();
    });

  }
  preload() {

    var self = this;

    this.http.post('http://localhost:8080/car/preload', this.form.search).subscribe((res: any) => {
      self.form.preload = res.result.rolelist;
      self.form.list = res.result.data;
      console.log("id>>>>>>",this.form.id)
      console.log(self.form.preload)
    })
  }
}



