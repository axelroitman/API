import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class ReclamosPorEstado extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        reclamos: {},
        isLoaded:false
      }
   }

   handlerClickItem(id) {
    this.props.history.push('/reclamo/' + id)
 }
 
  handleSubmit = (event) => {
      event.preventDefault();
      var estado = document.getElementById("estados").value;
      console.log(estado)
      if(estado > 0)
      {
        console.log(estado)
          fetch('http://localhost:8080/apitp/getReclamosPorEstado?estado=' + estado) 
          .then((res) => res.json()).then((json) => {
              this.setState({
              isLoaded: true,
              reclamos: json
              });
        }).catch((error) =>{
          alert("Error en API" + error);
        }); 
      }    
   }

  render() {
      var  {isLoaded, reclamos} = this.state;
      if(!isLoaded){
              return (
                    <form onSubmit={this.handleSubmit}>
                        <h1>Ver reclamos por estado</h1>
                        <select id="estados">
                          <option value="-1">Seleccione un estado</option>
                          <option value="1">Nuevo</option>
                          <option value="2">Abierto</option>
                          <option value="3">En Proceso</option>
                          <option value="4">Desestimado</option>
                          <option value="5">Anulado</option>
                          <option value="6">Terminado</option>

                       </select>
                       <button type="submit">Ver reclamos</button>
                      </form>
                      );
                    }
      else{
        if (reclamos.length == 0){
          return <p>No hay reclamos con el estado seleccionado.</p>
        }
        else{
         return (
          <div>
              <ul className="listReclamos">
             {
               reclamos.map(item => {
                return item.unidad != null ?
                 <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.edificio.nombre}, {item.unidad.piso}° {item.unidad.numero}</li>
                 :
                 <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.edificio.nombre}, {item.ubicacion}</li>

              })
             }
              </ul>

            </div>
       );
      }
    }
  }
}
export default ReclamosPorEstado;


