import React, {Component} from 'react';

class ReclamosPorPersona extends Component {
    constructor(props) {
      super(props);
      this.state = {
          documento: "",
          reclamos: [],
          personas: [],
          isLoaded: false,
          cargado: false
        };
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    componentDidMount() {
      fetch('http://localhost:8080/apitp/getPersonas')
      .then((res) => res.json()).then((json) => {
         this.setState({
          personas: json
        });
      }).catch((error) =>{
        alert("Error en API" + error);
      });
    }
    
    
    handleChange(event) {
      var valorSel = event.target.value;
      this.setState({documento: valorSel})
    }
  
    
    handleSubmit(event) {
      console.log(document.getElementById("listaPersonas"));

      this.setState({cargado: true})
      const {documento} = this.state;
      fetch('http://localhost:8080/apitp/getReclamosPorPersona?documento=' + documento)
        .then((res) => res.json()).then((json) => {
           this.setState({
            isLoaded: true,
            reclamos: json,
          });
  
        }).catch((error) =>{
          alert("Error en API" + error);
        });
       
       event.preventDefault();

    }
   
    handlePageChange(id) {
      this.props.history.push('/reclamo/' + id)
   }
  
    render() {
      
      var  {isLoaded, reclamos, personas} = this.state;
      console.log(reclamos)

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div className="container">Cargando...</div>
    }
    else if (reclamos.length == 0)
     {
        return(
          <div className="containerTabla">
            <p>La persona seleccionada no tiene ningún reclamo.</p>
          </div>
        );
     }
    else{
      return (
        <div className="containerTabla">
        <h2>Reclamos de {reclamos[0].usuario.nombre}</h2>
        <div className="tabla">
          <table>
                  <tr>
                  <th>N° Reclamo</th>
                  <th>Edificio</th>
                  <th>Unidad/Ubicación</th>
                  <th>Estado</th>
                  <th>Reclamo</th>

                  </tr>
                  {
                    reclamos.map(item => {
                      return item.unidad != null ?
                      <tr>
                      <td>#{item.numero} </td>
                      <td>{item.edificio.nombre}</td>
                      <td>{item.unidad.piso}° {item.unidad.numero}</td>
                      <td>{item.estado}</td>
                      <td><button onClick={this.handlePageChange.bind(this,item.numero)}>Ver</button></td>
                      </tr>

                      :                    
                      <tr>
                      <td>#{item.numero} </td>
                      <td>{item.edificio.nombre}</td>
                      <td>{item.ubicacion}</td>
                      <td>{item.estado}</td>
                      <td><button onClick={this.handlePageChange.bind(this,item.numero)}>Ver</button></td>
                      </tr>
                    })
                  }  
              
              </table>
          </div>
      </div>
       );
     }
    }
     else{
      return (

        <div className="container">
          <h2>Reclamos por persona</h2>
          <form onSubmit={this.handleSubmit}>
            <select id="listaPersonas" onChange={this.handleChange}>
                    <option value="-1">Seleccione a una persona</option>

                    {
                      personas.map(item => (
                          <option value={item.documento}>{item.nombre}</option>

                      ))
                    }
                </select>
            <input type="submit" value="Buscar" />
          </form>
        </div>
        );
    }
  }
}
  export default ReclamosPorPersona;