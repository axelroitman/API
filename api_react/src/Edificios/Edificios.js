import React, {Component} from 'react';

 class Edificio extends Component {

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
      <div>
      <ul className="listEdificios">
               {
                  edificios.map(item => (
                     <li key={item.id} onClick={this.handlerClickItem.bind(this,item.codigo)}>Nombre:  {item.nombre}</li>
                  ))
               }
            </ul>

      </div>
     );
   }
  }
}
export default Edificios;