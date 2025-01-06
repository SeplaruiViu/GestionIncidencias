import { Pipe, PipeTransform } from '@angular/core';
import { Auditoria } from '../modelos/auditoria-interface';

@Pipe({
  name: 'filtrarPipe',
  standalone: true
})
export class FiltrarPipePipe implements PipeTransform {

  // transform(value: unknown, ...args: unknown[]): unknown {
  //   return null;
  // }
  transform(items: Auditoria[], terminoBusqueda: string): Auditoria[] {
      if(!items) {
        return [];
      }

      if(!terminoBusqueda) {
        return items;
      }

      terminoBusqueda = terminoBusqueda.toLowerCase();
      return items.filter(item => {
        const matchString = `${item.idAuditoria} ${item.accion} ${item.endPoint} ${item.usuario}`
        return matchString.indexOf(terminoBusqueda) !==-1;
      });
  }

}
