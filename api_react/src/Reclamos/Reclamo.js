import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class Reclamo extends Component {
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
           isLoaded: true,
           reclamo: json
         });
       }).catch((error) =>{
         alert("Error en API" + error);
       });
   }


  render() {
      var  {isLoaded, reclamo} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
         var ubicacion = "";
         
         if(reclamo.ubicacion == null)
         {
            ubicacion = reclamo.unidad.piso + "° " + reclamo.unidad.numero;   
         }
         else
         {
            ubicacion = reclamo.ubicacion;   

         }
         if(sessionStorage.getItem("administrador") === "true"){
            return (
            
               <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
               <p>Usuario: {reclamo.usuario.nombre}</p>
               <p>Edificio: {reclamo.edificio.nombre}</p>
               <p>Descripcion: {reclamo.descripcion}</p>
               <p>Estado: {reclamo.estado}</p>
               <p>Imagenes: <strong>*IMAGENES*</strong></p>
   
               
               <button onClick={this.props.history.goBack}>Volver</button>
               </div>
            );
         }
         else{
         return (
            
            <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
            <p>Edificio: {reclamo.edificio.nombre}</p>
            <p>Descripcion: {reclamo.descripcion}</p>
            <p>Estado: {reclamo.estado}</p>
            <p>Imagenes: <strong>*IMAGENES*</strong></p>

            
            <button onClick={this.props.history.goBack}>Volver</button>
            </div>
            );
         }
      }
   }
}
export default Reclamo;


