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
      <div>
      <ul className="listPersonas">
               {
                  personas.map(item => (
                     <li key={item.id} onClick={this.handlerClickItem.bind(this,item.documento)}> {item.nombre}</li>
                  ))
               }
            </ul>

      </div>
     );
   }
  }
}
export default Personas;