import React, {Component} from 'react';

class ReclamosPorEdificio extends Component {
    constructor(props) {
      super(props);
      this.state = {
          codigo: "",
          reclamos: [],
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

      fetch('http://localhost:8080/apitp/getReclamosPorEdificio?codigo=' + codigo)
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
   
    handlerClickItem(id) {
      this.props.history.push('/reclamo/' + id)
   }
  
    render() {
      
      var  {isLoaded, reclamos, edificios} =this.state;
      console.log(reclamos)

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div className='container'>Cargando...</div>
    }
    else if (reclamos.length == 0)
     {
        return(
          <div className='container'>
           <h2>Reclamos por edificio</h2>
          <p>No hay reclamos en el edificio seleccionado.</p>
          </div>
        );
     }
    else{
      return (
        <div className="containerTabla">
              <h2>Reclamos por edificio</h2>
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
                            <td><button onClick={this.handlerClickItem.bind(this,item.numero)}>Ver</button></td>
                            </tr>
                          :
                            <tr>
                            <td>#{item.numero} </td>
                            <td>{item.edificio.nombre}</td>
                            <td>{item.ubicacion}</td>
                            <td>{item.estado}</td>
                            <td><button onClick={this.handlerClickItem.bind(this,item.numero)}>Ver</button></td>
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
        <form onSubmit={this.handleSubmit}>
           <div className='container'>
             <h2>Reclamos por edificio</h2>
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
  export default ReclamosPorEdificio;