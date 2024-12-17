import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirmar-eliminar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './confirmar-eliminar.component.html',
  styleUrl: './confirmar-eliminar.component.css'
})
export class ConfirmarEliminarComponent {
  // @Output() confirmar = new EventEmitter();
  // @Output() cancelar = new EventEmitter();
  // @Input() isOpen = false;
  // @Output() confirm = new EventEmitter<boolean>();

  constructor(public activeModal: NgbActiveModal) {}


  // onConfirm():void {
  //   this.confirm.emit(true);
  // }

  // onCancel():void {
  //   this.confirm.emit(false);
  // }


}
