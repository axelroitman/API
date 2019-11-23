import React, {Component} from 'react';

 class Reclamos extends Component {

    constructor(props) {
       super(props);
       this.state  = {
        personas: [],
           isLoaded:false
       }
    }

    componentDidMount() {
        fetch('http://localhost:8080/apitp/getReclamosPorPersona')
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
       this.props.history.push('/reclamo/' + id)
    }

  render() {

    var  {isLoaded, personas} =this.state;

    if(!isLoaded) {
        return <div>Loading...</div>
    }
   else
   {
    return (
      <div>
      <ul className="listReclamos">
               {
                  personas.map(item => (
                     <li key={item.id} onClick={this.handlerClickItem.bind(this,item.nombre)}> {item.nombre}</li>
                  ))
               }
            </ul>
      </div>
     );
   }
  }
}
export default Personas;