import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent {

  form: any = {
    id: null,
    ownerName: '',
    carName: '',
    model: '',
    purchaseDate: '',
    price: '',
    message: ''

  }
  fileToUpload: any = null;


  constructor(private http: HttpClient, private route: ActivatedRoute) {

    route.params.subscribe(params => {

      this.form.id = params["id"];

      this.display();
    })
  }
  ngOnInit(): void {

  }
  display() {
    var self = this;
    this.http.get("http://localhost:8080/car/get/" + this.form.id).subscribe((res: any) => {
      self.myFile();
      self.form = res.result.data;


    })
  }

  save() {
    var self = this;
    this.http.post("http://localhost:8080/car/save/", this.form).subscribe((res: any) => {
      self.myFile();
      self.form.message = res.result.message;



    })
  }
  onFileSelect(event: any) {
    this.fileToUpload = event.target.files.item(0);
    console.log(this.fileToUpload);
  }

  myFile() {
    var self = this;
    const formData = new FormData();
    formData.append('file', this.fileToUpload);
    return this.http.post("http://localhost:8080/car/profilePic/" + this.form.id, formData).subscribe(data => {
      console.log(this.fileToUpload);
    }, error => {
      console.log(error);
    });;
  }
  convertISODate(isoDate: string): string {
    return isoDate.split('T')[0];
  }
}

