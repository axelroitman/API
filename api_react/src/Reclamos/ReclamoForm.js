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
      fetch('http://localhost:8080/apitp/getEdificiosParaReclamosUsuario?documento=' + localStorage.getItem("documento"))
      .then((res) => res.json()).then((json) => {
         this.setState({
            isLoaded: true,
            edificios: json
            });
            console.log(json);
      }).catch((error) =>{
        alert("Error en API" + error);
      });
 }


  render() {
      var  {isLoaded, reclamo, edificios} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {

         return (
            <select id="listaEdificios">
               {
                  edificios.map(item => (
                     <option value={item.codigo}>{item.nombre}</option>

                  ))
               }
            </select>

         );
         /*var ubicacion = "";
         
         if(reclamo.ubicacion === null)
         {
            ubicacion = ubicacion.unidad.piso + "° " + ubicacion.unidad.numero;   
         }
         else
         {
            ubicacion = reclamo.ubicacion;   

         }
         return (
            <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
            <p>Edificio: {reclamo.edificio.nombre}</p>
            <p>Ubicacion: {ubicacion}</p>
            <p>Descripcion: {reclamo.descripcion}</p>
            <p>Estado: {reclamo.estado}</p>
            <p>Imagenes: <strong>*IMAGENES*</strong></p>

            
            <Link  to="/reclamos">Volver</Link>
            </div>
         );*/
      }
   }
}
export default ReclamoForm;


