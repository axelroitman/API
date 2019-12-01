import React, {Component} from 'react';

class UnidadesPorInquilino extends Component {
    constructor(props) {
      super(props);
      this.state = {
          documento: "",
          unidades: [],
          isLoaded: false,
        };
  
    }
  
    
    componentDidMount() {
      const {documento} = this.state;
      fetch('http://localhost:8080/apitp/getUnidadesPorInquilino?documento=' + sessionStorage.getItem("documento"))
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            unidades: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
       
    }

  
    render() {
      
      var  {isLoaded, unidades, cargado} = this.state;

    if(!isLoaded) {
        return <div className='container'>Cargando...</div>
     }   
     else if (unidades.length == 0)
     {
        return(
          <div className='container'><p>No hay unidades para mostrar.</p></div>
        );
     }
     else{
      return (
         
          <div className='container'>
             <h2>Mis Alquileres</h2>
             <div className="tabla">
              <table>
                    <tr>
                    <th>Edificio</th>
                    <th>Piso</th>
                    <th>N°</th>
                    </tr>
             {
               unidades.map(item => { 
               return <tr> <td>{item.edificio.nombre}</td><td>{item.piso}</td><td>{item.numero}</td> </tr>})
             }
              </table>
              </div>
             </div>

            );
        }
     }
  }
  export default UnidadesPorInquilino;