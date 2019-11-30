import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'
import Image from 'react-bootstrap/Image'

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
         console.log(reclamo);
         var ubicacion = "";
         var imagenesLinks = [];

         if(reclamo.ubicacion == null)
         {
            ubicacion = reclamo.unidad.piso + "° " + reclamo.unidad.numero;   
         }
         else
         {
            ubicacion = reclamo.ubicacion;   

         }

         reclamo.imagenes.forEach(function(img){
            imagenesLinks.push("http://api.axel.dx.am/" + img.direccion + "." + img.tipo);
         });


         if(sessionStorage.getItem("administrador") === "true"){
            return (
            
               <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
               <p>Estado: <b>{reclamo.estado}</b></p>
               <p>Usuario: {reclamo.usuario.nombre}</p>
               <p>Ubicacion: {reclamo.edificio.nombre}, {ubicacion}</p>
               <p>Descripcion: {reclamo.descripcion}</p>
               <p>Imagenes: <strong>*IMAGENES*</strong></p>
               
               {
                  imagenesLinks.map(item => (
                     <img src={item} />   
                  ))
                 
               }
               
               <button onClick={this.props.history.goBack}>Volver</button>
               </div>
            );
         }
         else{
         return (
            
            <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
            <p>Estado: <b>{reclamo.estado}</b></p>
            <p>Ubicacion: {reclamo.edificio.nombre}, {ubicacion}</p>
            <p>Descripcion: {reclamo.descripcion}</p>
            <p>Imagenes: <strong>*IMAGENES*</strong></p>

            {
                  imagenesLinks.map(item => (
                     <img src={item} />   
                  ))
                 
            }

            <button onClick={this.props.history.goBack}>Volver</button>
            </div>
            );
         }
      }
   }
}
export default Reclamo;


