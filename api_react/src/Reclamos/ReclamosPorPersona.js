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
   
    handlerClickItem(id) {
      this.props.history.push('/reclamo/' + id)
   }
  
    render() {
      
      var  {isLoaded, reclamos, personas} = this.state;
      console.log(reclamos)

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div>Cargando...</div>
    }
    else if (reclamos.length == 0)
     {
        return(
          <p>La persona seleccionada no tiene ningún reclamo.</p>
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
                 <li key={item.id} onClick={this.handlerClickItem.bind(this,item.numero)}> #{item.numero} - {item.edificio.nombre}, {item.ubicacion}</li>

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
      );
    }
  }
}
  export default ReclamosPorPersona;