import { Component } from '@angular/core';
import { evaluate } from 'mathjs';

@Component({
  standalone: true,
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent {
  displayValue: string = '';

  appendToDisplay(value: string) {
    this.displayValue += value;
  }

  clearDisplay() {
    this.displayValue = '';
  }

  calculateResult() {
    try {
      this.displayValue = evaluate(this.displayValue);
    } catch (e) {
      this.displayValue = 'Error';
    }
  }
}
