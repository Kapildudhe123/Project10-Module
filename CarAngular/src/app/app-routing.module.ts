import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbaarComponent } from './navbaar/navbaar.component';
import { CarComponent } from './car/car.component';
import { CarlistComponent } from './carlist/carlist.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'navbaar',
    pathMatch: 'full'
  },
  {
    path: 'navbaar',
    component: NavbaarComponent
  },
  {
    path: 'car',
    component: CarComponent
  },
  {
    path: 'car/:id',
    component: CarComponent
  },
  {
    path: 'carlist',
    component: CarlistComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
