import React, {Component} from 'react';

 class TodosLosReclamos extends Component {

    constructor(props) {
       super(props);
       this.state  = {
         reclamos: [],
           isLoaded:false,
       }
    }

    componentDidMount() {

        fetch('http://localhost:8080/apitp/getAllReclamos')
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            reclamos: json
          });
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }

    handlerClickItem(id) {
       this.props.history.push('/reclamo/' + id)
    }

  render() {

    var  {isLoaded, reclamos} =this.state;

    if(!isLoaded) {
        return <div className='container'>Cargando...</div>
    }
   else
   {
     if (reclamos.length == 0)
     {
        return(
          <div className='container'><p>No hay reclamos para mostrar.</p></div>
        );
     }
     else{
      return (
        <div className="containerTabla">
          <h2>Todos los reclamos</h2>
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
export default TodosLosReclamos;