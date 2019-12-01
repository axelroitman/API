import React, {Component} from 'react';

 class Personas extends Component {

    constructor(props) {
       super(props);
       this.state  = {
        personas: [],
           isLoaded:false
       }
    }

    componentDidMount() {
        fetch('http://localhost:8080/apitp/getPersonas')
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            personas: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }
    

    handlerClickItem(id) {
       this.props.history.push('/persona/' + id)
    }

  render() {

    var  {isLoaded, personas} =this.state;

    if(!isLoaded) {
        return <div>Loading...</div>
    }
   else
   {
    return (
      <div className='container'>
         <h2>Todas las personas</h2>
         <div className="tabla">
              <table>
                    <tr>
                    <th>Nombre</th>
                    <th>Documento</th>
                    <th>Persona</th>
                    </tr>

                   {
                  personas.map(item => {
                     return (
                     <tr>
                     <td>{item.nombre}</td>
                     <td>{item.documento}</td>
                     <td><button onClick={this.handlerClickItem.bind(this,item.documento)}>Ver</button></td>
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
export default Personas;