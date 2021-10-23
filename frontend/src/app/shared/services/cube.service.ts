import { Injectable } from '@angular/core';
import { ResultSet } from '@cubejs-client/core';
import { CubejsClient } from '@cubejs-client/ngx';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CubeService {

  constructor(private cube: CubejsClient) { }

  cubeRequest(query: any): Observable<ResultSet<any>> {
    return this.cube.load(query);
  }
}
