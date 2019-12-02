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
                      <div className="containerTabla">
                         <h2>Reclamos por estado</h2>
                      
                        <select id="estados">
                          <option value="-1">Seleccione un estado</option>
                          <option value="1">Nuevo</option>
                          <option value="2">Abierto</option>
                          <option value="3">En Proceso</option>
                          <option value="4">Desestimado</option>
                          <option value="5">Anulado</option>
                          <option value="6">Terminado</option>

                       </select>
                       <br />
                       <br />
                       <button type="submit">Ver reclamos</button>
                       </div>
                      </form>
                      );
                    }
      else{
        if (reclamos.length == 0){
          return (
            <div className="containerTabla">
            <h2>Reclamos por estado</h2>
            <p>No hay reclamos con el estado seleccionado.</p>
            </div>);
        }
        else{
          return (
            <div className="containerTabla">
              <h2>Reclamos por estado</h2>
              <div className="tabla">
                <table>
                        <tr>
                        <th>N° Reclamo</th>
                        <th>Edificio</th>
                        <th>Unidad/Ubicación</th>
                        <th>Estado</th>
                        <th>Reclamo</th>
    
                        </tr>
                        {
                          reclamos.map(item => {
                            return item.unidad != null ?
                            <tr>
                            <td>#{item.numero} </td>
                            <td>{item.edificio.nombre}</td>
                            <td>{item.unidad.piso}° {item.unidad.numero}</td>
                            <td>{item.estado}</td>
                            <td><button onClick={this.handlerClickItem.bind(this,item.numero)}>Ver</button></td>
                            </tr>
    
                            :                    
                            <tr>
                            <td>#{item.numero} </td>
                            <td>{item.edificio.nombre}</td>
                            <td>{item.ubicacion}</td>
                            <td>{item.estado}</td>
                            <td><button onClick={this.handlerClickItem.bind(this,item.numero)}>Ver</button></td>
                            </tr>
                          })
                         }  
                    
                    </table>
            </div>
    
            </div>
           );
      }
    }
  }
}
export default ReclamosPorEstado;


