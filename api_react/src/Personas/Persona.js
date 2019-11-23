import React, {Component} from 'react';
import './Personas.css';
import { Link } from 'react-router-dom'

class Persona extends Component {
  render() {
     return (
        <div className="personas"><h2 className="selectedPersona">Detalle de la persona: "{this.props.match.params.id}"</h2>  
        <p><b><u>Ejercicio:</u></b><br></br> Implementar la llamada a la API con  el ID de la Movie y mostrar el detalle cuando se monta el componente.</p>
          <Link  to="/personas">Volver</Link>
       </div>
    );
   }
}
export default Persona;


