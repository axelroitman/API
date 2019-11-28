import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class ReclamoForm extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        reclamo: {},
        edificios: [],
        isLoaded:false
      }
   }
  // getEdificiosParaReclamosUsuario
   componentDidMount() {
      fetch('http://localhost:8080/apitp/getEdificiosParaReclamosUsuario?documento=' + sessionStorage.getItem("documento"))
      .then((res) => res.json()).then((json) => {
         var listadoEdificios = [];
         json.forEach(function(unidad){
            var aparecio = false;
            listadoEdificios.forEach(function(edificio){
               if(edificio.nombre == unidad.edificio.nombre)
               {
                  aparecio = true;
               }
            });

            if(aparecio == false)
            {
               listadoEdificios.push(unidad.edificio);
            }
         });

         this.setState({
            isLoaded: true,
            unidades: json,
            edificios: listadoEdificios
            });
            console.log(json);
      }).catch((error) =>{
        alert("Error en API" + error);
      });
 }
   handleChange = (event) => {
      console.log(event);
      //Busco las unidades y las muestro.
      //this.setState({ value: event.target.value });
   };


  render() {
      var  {isLoaded, reclamo, edificios} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
         return (
            <select id="listaEdificios" onChange={this.handleChange}>
               <option value="-1">Seleccione un edificio</option>

               {
                  edificios.map(item => (
                     <option value={item.codigo}>{item.nombre}</option>

                  ))
               }
            </select>

         );

      }
   }
}
export default ReclamoForm;


