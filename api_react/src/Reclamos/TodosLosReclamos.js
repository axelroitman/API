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
        return <div>Loading...</div>
    }
   else
   {
     if (reclamos.length == 0)
     {
        return(
          <p>No hay reclamos para mostrar.</p>
        );
     }
     else{
      return (
        <div>
        <ul className="listReclamos">
                 {
                    reclamos.map(item => {
                      return item.unidad != null ?
                       <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}>Usuario: {item.usuario.nombre}, nro. {item.numero} - {item.edificio.nombre}, {item.unidad.piso}° {item.unidad.numero}</li>
                       :
                       <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.ubicacion}</li>

                    })
                 }
              </ul>
        </div>
       );
     }
   }
  }
}
export default TodosLosReclamos;