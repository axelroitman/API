import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class CambiarEstado extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        reclamo: {},
        isLoaded:false
      }
   }

   componentDidMount() {
      fetch('http://localhost:8080/apitp/getReclamosPorNumero?numero=' + this.props.match.params.id)
      .then((res) => res.json()).then((json) => {

         this.setState({
          reclamo: json,
          isLoaded: true
        });

      }).catch((error) =>{
        alert("Error en API" + error);
      });
  }
  handleSubmit = (event) => {
      event.preventDefault();
      var estado = document.getElementById("estados").value;
      var actualizacion = document.getElementById("actualizacion").value;
      if(estado > 0)
      {
         if(actualizacion != "")
         {
            actualizacion = actualizacion.split('|@@|');
            actualizacion = actualizacion[0].split('|/|');
            if(actualizacion.length == 1)
            {
               fetch('http://localhost:8080/apitp/cambiarEstado?numero=' + this.props.match.params.id + '&estado=' + estado + '&actualizacion=' + actualizacion + '&nombre=' + sessionStorage.getItem("nombre"), {
                  method: 'PUT' 
               }).then(response => {
                  console.log(response);
                  if (response.status === 200) 
                  {
                     if(sessionStorage.getItem("administrador") == "true")
                     {
            
                        alert("Estado cambiado.");
                     }
                     else
                     {
                        alert("Reclamo anulado");
                     }
                     window.location = "/reclamo/" + this.props.match.params.id;
                  }
                  else if (response.status === 409)
                  {
                     alert("Error al crear el usuario.");
                  }
               })
               .catch(error => {
                  console.log("Error", error);
               });      
            }
            else
            {
               alert("Su actualización contiene una cadena de caracteres inválida");
            }
         }
         else
         {
            alert("Debe estcribir una actualizacion")
         }
      }
      else
      {
         alert("Debe seleccionar un estado");
      }
     
   }
   handleChangeInput = event => {
      const { value, maxLength } = event.target;
      const message = value.slice(0, maxLength);
  
      this.setState({
        form: {
          message
        }
      });
    };

  render() {
      var  {isLoaded, reclamo, unidadesListadas} =this.state;

      if(!isLoaded) {
         return <div>Cargando...</div>
      }
      else
      {
         if(sessionStorage.getItem("administrador") == "true")
         {
            return (
               <form id="frm-estados" onSubmit={this.handleSubmit}>
                  <h1>Cambiar estado</h1>
                  <select id="estados">
                     <option value="-1">Seleccione un estado</option>
                     {reclamo.estado == "nuevo" ? (
                     <option value="2">Abierto</option>
                     ) : (
                     ''
                        )
                     }
                     {reclamo.estado == "nuevo" || reclamo.estado == "abierto" ? (
                     <option value="3">En proceso</option>
                     ) : (
                     ''
                        )
                     }
                     <option value="4">Desestimado</option>
                     <option value="6">Terminado</option>
                  </select>
                  <textarea onChange={this.handleChangeInput} name="actualizacion" id="actualizacion" rows="10" cols="50" maxLength="800" placeholder="Actualizacion"></textarea>
                  <button type="submit">Cambiar estado</button>
   
               </form>
            );
   
         }
         else
         {
            return (
               <form id="frm-estados" onSubmit={this.handleSubmit}>
                  <h1>Anular reclamo</h1>

                  <input type="hidden" name="estados" id="estados" value="5"/>

                  <textarea onChange={this.handleChangeInput} name="actualizacion" id="actualizacion" rows="10" cols="50" maxLength="800" placeholder="Actualizacion"></textarea>
                  <button type="submit">Anular estado</button>
   
               </form>
            );
         }


      }
   }
}
export default CambiarEstado;


