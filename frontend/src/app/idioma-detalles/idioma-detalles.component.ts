import { Component } from '@angular/core';
import { Idioma } from '../idioma';
import { ActivatedRoute } from '@angular/router';
import { IdiomaService } from '../idioma.service';

@Component({
  selector: 'app-idioma-detalles',
  standalone: false,
  templateUrl: './idioma-detalles.component.html',
  styleUrl: './idioma-detalles.component.css'
})
export class IdiomaDetallesComponent {

  id: number;
  Idioma: Idioma;

  constructor(private route: ActivatedRoute, private idiomaServicio: IdiomaService) {
  
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.Idioma = new Idioma();
    this.idiomaServicio.obternerIdiomaPorId(this.id).subscribe(dato => {
      this.Idioma = dato;
    });
  }

  
}
