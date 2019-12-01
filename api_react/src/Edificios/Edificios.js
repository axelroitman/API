import React, {Component} from 'react';

 class Edificios extends Component {

    constructor(props) {
       super(props);
       this.state  = {
        edificios: [],
           isLoaded:false
       }
    }

    componentDidMount() {
        fetch('http://localhost:8080/apitp/getEdificios')
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            edificios: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }
    

    handlerClickItem(id) {
       this.props.history.push('/edificio/' + id)
    }

  render() {

    var  {isLoaded, edificios} =this.state;

    if(!isLoaded) {
        return <div>Loading...</div>
    }
   else
   {
    return (
      <div className="containerTabla">
         <h2>Listado de edificios</h2>
         <div className="tabla">
              <table>
                    <tr>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    </tr>

                   {
                  edificios.map(item => {
                     return (
                     <tr>
                     <td>{item.nombre}</td>
                     <td>{item.direccion}</td>
                     </tr>
                  );})
               }
            </table>
         </div>
      </div>
     );
   }
  }
}
export default Edificios;