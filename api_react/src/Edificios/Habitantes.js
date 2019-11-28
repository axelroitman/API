import React, {Component} from 'react';

class Habitantes extends Component {
    constructor(props) {
      super(props);
      this.state = {
          codigo: "",
          habitantes: [],
          isLoaded: false,
          cargado: false
        };
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    
    handleChange(event) {
      this.setState({[event.target.name]: event.target.value});
    }
  
    
    handleSubmit(event) {
      this.setState({cargado: true})
      const {codigo} = this.state;
      fetch('http://localhost:8080/apitp/getHabitantesPorEdificio?codigo=' + codigo)
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            habitantes: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
       
       event.preventDefault();

    }
   
    handlerClickItem(id) {
      this.props.history.push('/persona/' + id)
   }
  
    render() {
      
      var  {isLoaded, habitantes} =this.state;
      console.log(habitantes)

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div>Loading...</div>
    }
    else{
      return (
          <div>
              <ul className="listHabitantes">
             {
                habitantes.map(item => (
                   <li key={item.id} onClick={this.handlerClickItem.bind(this,item.documento)}> {item.nombre}</li>
                ))
             }
              </ul>

    </div>
       );
     }
    }
     else{
      return (


        <form onSubmit={this.handleSubmit}>
          <label>
            Código del edificio:
            <input type="text" name="codigo" value={this.state.codigo} onChange={this.handleChange} required />
          </label>
          <input type="submit" value="Submit" />
        </form>
      );
    }
  }
}
  export default Habitantes;