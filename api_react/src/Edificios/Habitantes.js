import React, {Component} from 'react';

class Habitantes extends Component {
    constructor(props) {
      super(props);
      this.state = {
          codigo: '',
          habitantes: [],
          isLoaded: false
        };
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    
    handleChange(event) {
        console.log(event)
      this.setState({codigo: event.target.codigo});
    }
  
    
    handleSubmit(event) {

        var  {isLoaded, habitantes} =this.state;

        if(!isLoaded) {
            return <div>Loading...</div>
        }
       else
       {
        return (
            <div>
                <ul className="listPersonas">
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
    componentDidMount() {
        fetch('http://localhost:8080/apitp/getHabitantesPorEdificio?codigo=' + this.props.match.params.id)
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            habitantes: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }
  
    render() {
      return (
        <form onSubmit={this.handleSubmit}>
          <label>
            Código del edificio:
            <input type="text" value={this.props.match.params.id} onChange={this.handleChange} />
          </label>
          <input type="submit" value="Submit" />
        </form>
      );
    }
  }
  export default Habitantes;