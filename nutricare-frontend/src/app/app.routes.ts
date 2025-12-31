import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { RegisterComponent } from './components/register/register';
import { authGuard } from './guards/auth.guard';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard';
import { AdminDiseasesComponent } from './components/admin/admin-diseases/admin-diseases';
import { AdminRulesComponent } from './components/admin/admin-rules/admin-rules';
import { AdminGuard } from './guards/admin.guard';
import { AdminHomeComponent } from './components/admin-home/admin-home';
import { UserLayoutComponent } from './components/user-layout/user-layout';
import { AdminCategoryRulesComponent } from './components/admin/admin-category-rules/admin-category-rules';
import { UserHomeComponent } from './components/user-home/user-home';
import { RecommendationComponent } from './components/recommendation/recommendation';
import { DiseaseSearchComponent } from './components/disease-selection/disease-search';
import { AdminFoodsComponent } from './components/admin/admin-foods/admin-foods';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  //auth
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  //admin home
  // { path: 'admin/home', component: AdminHomeComponent },

  // USER AREA (WITH LAYOUT)
  {
    path: 'user',
    component: UserLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'home', component: UserHomeComponent },
      { path: 'search-diseases', component: DiseaseSearchComponent },
      { path: 'recommend', component: RecommendationComponent }
    ]
  },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [authGuard, AdminGuard],   // ✅ BOTH GUARDS HERE
    children: [
      { path: '', redirectTo: 'diseases', pathMatch: 'full' }, // default
      { path: 'home', component: AdminHomeComponent},
      { path: 'diseases', component: AdminDiseasesComponent },
      { path: 'rules', component: AdminRulesComponent },
      // later: rules, dashboard, etc.
      { path: 'foods', component: AdminFoodsComponent },
      { path: 'category-rules', component: AdminCategoryRulesComponent }
    ]
  }
];

