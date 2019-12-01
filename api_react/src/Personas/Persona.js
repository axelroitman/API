import React, {Component} from 'react';
import './Personas.css';
import { withRouter } from 'react-router-dom';
import { Link } from 'react-router-dom'

class Persona extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        persona: {},
        isLoaded:false
      }
   }

   componentDidMount() {
       fetch('http://localhost:8080/apitp/getPersonaPorDocumento?documento=' + this.props.match.params.id)
       .then((res) => res.json()).then((json) => {
 
          this.setState({
           isLoaded: true,
           persona: json
         });
       }).catch((error) =>{
         alert("Error en API" + error);
       });
   }


  render() {
      var  {isLoaded, persona} = this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
        return (
         <div className="personas container"><h2 className="selectedPersona">Detalle de la persona: "{persona.nombre}"</h2>
            <p>Documento: {persona.documento}</p>
            <p>Usuario: {persona.usuario == null ? "-" : persona.usuario}</p>
            <p>Rol: {persona.administrador == true ? "Administrador" : "Usuario"}</p>
            <button onClick={this.props.history.goBack}>Volver</button>

            </div>
         );
      }
   }
}
export default Persona;


