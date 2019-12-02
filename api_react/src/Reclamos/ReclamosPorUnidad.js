import React, {Component} from 'react';

class ReclamosPorUnidad extends Component {
    constructor(props) {
      super(props);
      this.state = {
          documento: "",
          reclamos: [],
          unidades: [],
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
    
    
    handleChange = (event) => {
        var valorSel = event.target.value;
    
        if(event.target.id === 'listaEdificios'){
          if(event.target.value != -1){
            this.setState({codigo: valorSel})
            fetch('http://localhost:8080/apitp/getUnidadesPorEdificio?codigo=' + valorSel)
            .then((res) => res.json()).then((json) => {
            this.setState({
                isLoaded: true,
                unidades: json
            });
            }).catch((error) =>{
            alert("Error en API" + error);
            });
        }
      }
    }
  
    
    handleSubmit(event) {
      event.preventDefault();
      this.setState({cargado: true})
      var edificio = document.getElementById("listaEdificios").value;
      var piso = null;
      var numero = null;
      if(edificio != -1)
      {
         var unidad = document.getElementById("listaUnidades").value;
         if(unidad == 0)
         {
            unidad = null;
         }

         if(unidad == -1)
         {
            alert("Debe seleccionar una unidad");
         }
         else
         {
            if(unidad != 0)
            {
               this.state.unidades.forEach(function(un){
                  if(un.piso + "° " + un.numero == unidad)
                  {
                     piso = un.piso;
                     numero = un.numero;
                  }
               });
      
            
            fetch('http://localhost:8080/apitp/getReclamosPorUnidad?codigo=' + edificio + '&piso=' + piso + '&numero=' + numero)
                .then((res) => res.json()).then((json) => {
                this.setState({
                    isLoaded: true,
                    reclamos: json,
                });
        
                }).catch((error) =>{
                alert("Error en API" + error);
                });
              }
        }
    }
}
   
    handlePageChange(id) {
      this.props.history.push('/reclamo/' + id)
   }
  
    render() {
      
      var  {isLoaded, reclamos, edificios, unidades} = this.state;

     if (this.state.cargado)
     {
      if(!isLoaded) {
        return (
        <div className='container'>
        <h2>Reclamos por unidad</h2>
        Cargando...
        </div>);
    }
    else if (reclamos.length == 0)
     {
        return(
          <div className='container'>
            <h2>Reclamos por unidad</h2>
          <p>La unidad seleccionada no tiene ningún reclamo.</p>
          </div>
        );
     }
    else{
      return (
        <div className="containerTabla">
    <h2>Reclamos de {reclamos[0].edificio.nombre}, {reclamos[0].unidad.piso}° {reclamos[0].unidad.numero}</h2>
        <div className="tabla">
          <table>
                  <tr>
                  <th>N° Reclamo</th>
                  <th>Estado</th>
                  <th>Reclamo</th>

                  </tr>
                  {
                    reclamos.map(item => {
                      return item.unidad != null ?
                      <tr>
                      <td>#{item.numero} </td>
                      <td>{item.estado}</td>
                      <td><button onClick={this.handlePageChange.bind(this,item.numero)}>Ver</button></td>
                      </tr>

                      :                    
                      <tr>
                      <td>#{item.numero} </td>
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


        <form onSubmit={this.handleSubmit}>
            <div className='container'>
            <h2>Reclamos por unidad</h2>

          <select id="listaEdificios" onChange={this.handleChange}>
                  <option value="-1">Seleccione un edificio</option>

                  {
                     edificios.map(item => (
                        <option value={item.codigo}>{item.nombre}</option>

                     ))
                  }
               </select>
               <br></br>
               <select id="listaUnidades" onChange={this.handleChange}>
               <option value="-1">Seleccione una unidad</option>

               {
                  unidades.map(item => (
                  <option value={item.identificador}> {item.piso}° {item.numero}</option>

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
  export default ReclamosPorUnidad;