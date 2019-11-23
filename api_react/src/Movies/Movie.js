import React, {Component} from 'react';
import './Movies.css';
import { Link } from 'react-router-dom'

class Movie extends Component {
  render() {
     return (
        <div className="movies"><h2 className="selectedMovie">Detalle de movie: "{this.props.match.params.id}"</h2>  
        <p><b><u>Ejercicio:</u></b><br></br> Implementar la llamada a la API con  el ID de la Movie y mostrar el detalle cuando se monta el componente.</p>
          <Link  to="/movies">Volver</Link>
       </div>
    );
   }
}
export default Movie;


