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

   handlePageChange(numReclamo) {
      window.location = '/cambiar_estado/' + numReclamo;
    }
  
  

  render() {
      var  {isLoaded, reclamo} =this.state;
      
      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
         var ubicacion = "";
         var imagenesLinks = [];
         var sinImagenes = "";
         var actualizaciones = "";

         if(reclamo.ubicacion == null)
         {
            ubicacion = reclamo.unidad.piso + "° " + reclamo.unidad.numero;   
         }
         else
         {
            ubicacion = reclamo.ubicacion;   

         }

         reclamo.imagenes.forEach(function(img){
            
            imagenesLinks.push({imagen : "https://grupo6api.000webhostapp.com/" + reclamo.numero + "_" + img.direccion + "." + img.tipo, alernativa: "https://grupo6api.000webhostapp.com/FtpTrial-" + reclamo.numero + "_" + img.direccion + "." + img.tipo});
         });

         var arrayActualizaciones = [""];
         if(reclamo.actualizacion != null)
         {
            arrayActualizaciones = reclamo.actualizacion.split('|@@|');
            var arrayTemp = [];
            arrayActualizaciones.forEach(function(act){
               act = act.split('|/|');
               arrayTemp.push(act);
               
               
            });

            if(arrayTemp != [])
            {
               arrayActualizaciones = arrayTemp;
            }
            
   
         }

         if(imagenesLinks.length == 0)
         {
            sinImagenes = "Sin imágenes";
         }
         if(actualizaciones == [""])
         {
            actualizaciones = "No hubieron actualizaciones de este reclamo.";
         }

         console.log(arrayActualizaciones);
         if(sessionStorage.getItem("administrador") === "true"){
            return (
            
               <div className="reclamos"><h2 className="selectedReclamo">Reclamo #{this.props.match.params.id}</h2>  
               <p>Estado: <b>{reclamo.estado}</b></p>
               <p>Usuario: {reclamo.usuario.nombre}</p>
               <p>Ubicacion: {reclamo.edificio.nombre}, {ubicacion}</p>
               <p>Descripcion: {reclamo.descripcion}</p>
               <p>Imagenes: {sinImagenes}</p>
               <div>
               {
                  imagenesLinks.map(item => (
                     <img style={{width: 100}} src={item.imagen} ref={img => this.img = img} onError={
                        () => this.img.src = item.alernativa
                     }/>
                  ))
               }
               </div>
               {reclamo.estado == "nuevo" || reclamo.estado == "abierto" || reclamo.estado == "enProceso"  ? (
                  <button onClick={this.handlePageChange.bind(this, reclamo.numero)}>Cambiar estado</button>
               ) : (
                  ''
               )
               }
               <p>Actualizaciones: {actualizaciones}</p>
               <div>
                  {
                     arrayActualizaciones.map(item => (
                        <div><b>{item[0]}</b>{item[1]}<b>{item[2]}</b>{item[3]}<br/>{item[4]}</div>
                        ))
                  }
               </div>

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
            <p>Imagenes: {sinImagenes}</p>
            <div>
               {
                  imagenesLinks.map(item => (
                     <img style={{width: 100}} src={item.imagen} ref={img => this.img = img} onError={
                        () => this.img.src = item.alernativa
                     }/>
                  ))
               }
            </div>
            <p>Actualizaciones: {actualizaciones}</p>
            <div>
               {
                  
                  arrayActualizaciones.map(item => (
                  <div><b>{item[0]}</b>{item[1]}<b>{item[2]}</b>{item[3]}<br/>{item[4]}</div>

                  ))
               }
            </div>

            
            <button onClick={this.props.history.goBack}>Volver</button>
            </div>
            );
         }
      }
   }
}
export default Reclamo;


