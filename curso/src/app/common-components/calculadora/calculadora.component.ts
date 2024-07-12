import { Component } from '@angular/core';
import { evaluate } from 'mathjs';

@Component({
  standalone: true,
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent {
  displayValue: string = '0';

  appendToDisplay(value: string) {
    if (this.displayValue == '0') {
      this.displayValue = ''
    }
    this.displayValue += value;
  }

  clearDisplay() {
    this.displayValue = '0';
  }

  calculateResult() {
    try {
      this.displayValue = evaluate(this.displayValue);
    } catch (e) {
      this.displayValue = 'Error';
    }
  }
}
