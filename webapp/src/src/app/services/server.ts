import { HttpHeaders } from "@angular/common/http";

export const server = "http://localhost:8080/";
export const serviceUrl = server + "racemanager-services/rest/";

export const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };