import { Routes } from '@angular/router';
import { Chansons } from './chansons/chansons';
import { AddChanson } from './add-chanson/add-chanson';
import { UpdateChanson } from './update-chanson/update-chanson';
import { RechercheParAlbumComponent } from './recherche-par-album/recherche-par-album';
import { RechercheParNomComponent } from './recherche-par-nom/recherche-par-nom';

import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { chansonGuard } from './chanson.guard';

export const routes: Routes = [
    {path: "chansons", component : Chansons},
    {path: "add-chanson", component : AddChanson, canActivate: [chansonGuard]},
    {path: "updateChanson/:id", component: UpdateChanson, canActivate: [chansonGuard]},
    {path: "rechercheParAlbum", component: RechercheParAlbumComponent},
    {path: "rechercheParNom", component: RechercheParNomComponent},
    {path: "login", component: LoginComponent},
    {path: "app-forbidden", component: ForbiddenComponent},
    {path: "", redirectTo: "chansons", pathMatch: "full"}
];
