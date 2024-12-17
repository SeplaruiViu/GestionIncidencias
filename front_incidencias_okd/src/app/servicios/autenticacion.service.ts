import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AutenticacionService {

  private apiURL = 'http://localhost:8080/api/login';
  constructor(private httpClient: HttpClient) { }

  login(usuario:string, password: string):Observable<any>{
    return this.httpClient.post(this.apiURL, {usuario:usuario, password:password});
  }
}
