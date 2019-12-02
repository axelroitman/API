import React, {Component} from 'react';

class ReclamosPorEdificioUsuario extends Component {
    constructor(props) {
      super(props);
      this.state = {
          codigo: "",
          reclamos: [],
          edificios: [],
          unidades:[],
          isLoaded: false,
          cargado: false
        };
  
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
  
    componentDidMount() {
      fetch('http://localhost:8080/apitp/getEdificiosParaReclamosUsuario?documento=' + sessionStorage.getItem("documento"))
      .then((res) => res.json()).then((json) => {
         var listadoEdificios = [];
         json.forEach(function(unidad){
            var aparecio = false;
            listadoEdificios.forEach(function(edificio){
               if(edificio.nombre == unidad.edificio.nombre)
               {
                  aparecio = true;
               }
            });

            if(aparecio == false)
            {
               listadoEdificios.push(unidad.edificio);
            }
         });

         this.setState({
            isLoaded: true,
            unidades: json,
            edificios: listadoEdificios
            });
      }).catch((error) =>{
        alert("Error en API" + error);
      });
    }
    
    
    handleChange(event) {
      if(event.target.id === 'listaEdificios'){
        
      var valorSel = event.target.value;
      this.setState({codigo: valorSel})
    
    }
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
   
    handlePageChange(id) {
      this.props.history.push('/reclamo/' + id)
   }
  
    render() {
      
      var  {isLoaded, reclamos, edificios} =this.state;
     if (this.state.cargado)
     {
      if(!isLoaded) {
        return <div className="container">Loading...</div>
    }
    else if (reclamos.length == 0)
     {
        return(
          <div className="container"><p>No hay reclamos en el edificio.</p></div>
        );
     }
    else{
      return (
        <div className="containerTabla">
          <h2>Reclamos del edificio {reclamos[0].edificio.nombre}</h2>
          <div className="tabla">
            <table>
                    <tr>
                    <th>N° Reclamo</th>
                    <th>Nombre de quien reclama</th>
                    <th>Unidad/Ubicación</th>
                    <th>Estado</th>
                    <th>Reclamo</th>

                    </tr>
                    {
                      reclamos.map(item => {
                        return item.unidad != null ?
                        <tr>
                        <td>#{item.numero} </td>
                        <td>{item.usuario.nombre}</td>
                        <td>{item.unidad.piso}° {item.unidad.numero}</td>
                        <td>{item.estado}</td>
                        <td><button onClick={this.handlePageChange.bind(this,item.numero)}>Ver</button></td>
                        </tr>

                        :                    
                        <tr>
                        <td>#{item.numero} </td>
                        <td>{item.usuario.nombre}</td>
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
          <h2>Buscar reclamos de un edificio</h2>
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
        </div>
      );
    }
  }
}
  export default ReclamosPorEdificioUsuario;