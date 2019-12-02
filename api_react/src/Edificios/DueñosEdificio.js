import React, {Component} from 'react';

class DueñosEdificio extends Component {
    constructor(props) {
      super(props);
      this.state = {
          codigo: "",
          duenios: [],
          edificios: [],
          isLoaded: false,
          cargado: false
        };
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    componentDidMount() {
      fetch('http://localhost:8080/apitp/getEdificios')
      .then((res) => res.json()).then((json) => {
         this.setState({
          edificios: json
        });
      }).catch((error) =>{
        alert("Error en API" + error);
      });
    }
    
    
    handleChange(event) {
      var valorSel = event.target.value;
      this.setState({codigo: valorSel})
        
    }
  
    
    handleSubmit(event) {
      this.setState({cargado: true})
      const {codigo} = this.state;

      fetch('http://localhost:8080/apitp/getDueniosPorEdificio?codigo=' + codigo)
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            duenios: json,
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
      
      var  {isLoaded, duenios, edificios} =this.state;
      console.log(duenios)

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div className='container'>
          <h2>Dueños por edificio</h2>
          Cargando...</div>
    }
    else if (duenios.length == 0)
     {
        return(
          <div className='container'>
            <h2>Dueños por edificio</h2>
            <p>El edificio seleccionado no contiene dueños.</p>
          </div>
        );
     }
    else{
      return (
        <div className='container'>
           <h2>Dueños por edificio</h2>
           <div className="tabla">
                <table>
                      <tr>
                      <th>Nombre</th>
                      <th>Documento</th>
                      <th>Persona</th>
                      </tr>
  
                     {
                    duenios.map(item => {
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
     else{
      return (


        <form onSubmit={this.handleSubmit}>
          <div className='container'>
            <h2>Dueños por edificio</h2>
          
          <select id="listaEdificios" onChange={this.handleChange}>
                  <option value="-1">Seleccione un edificio</option>

                  {
                     edificios.map(item => (
                        <option value={item.codigo}>{item.nombre}</option>

                     ))
                  }
               </select>
               <br></br>
               <br></br>
          <input type="submit" value="Buscar" />
          </div>
        </form>
      );
    }
  }
}
  export default DueñosEdificio;