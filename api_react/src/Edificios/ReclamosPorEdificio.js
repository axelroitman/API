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
        return <div>Loading...</div>
    }
    else if (reclamos.length == 0)
     {
        return(
          <p>No hay reclamos en el edificio seleccionado.</p>
        );
     }
    else{
      return (
          <div>
              <ul className="listReclamos">
                 {
                    reclamos.map(item => {
                      return item.unidad != null ?
                       <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.edificio.nombre}, {item.unidad.piso}° {item.unidad.numero}</li>
                       :
                       <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.ubicacion}</li>

                    })
                 }
              </ul>

    </div>
       );
     }
    }
     else{
      return (


        <form onSubmit={this.handleSubmit}>
          <select id="listaEdificios" onChange={this.handleChange}>
                  <option value="-1">Seleccione un edificio</option>

                  {
                     edificios.map(item => (
                        <option value={item.codigo}>{item.nombre}</option>

                     ))
                  }
               </select>
          <input type="submit" value="Buscar" />
        </form>
      );
    }
  }
}
  export default ReclamosPorEdificio;