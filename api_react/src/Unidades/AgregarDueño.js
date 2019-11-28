import React, {Component} from 'react';
import { Link } from 'react-router-dom'

class AgregarDueño extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        edificios: [],
        unidades: [],
        personas: [],
        codigo: "",
        piso: "",
        numero: "",
        documento:"",
        isLoaded:false
      }
      this.handleSubmit = this.handleSubmit.bind(this);
	  this.handleChange = this.handleChange.bind(this);
   }
  // getEdificiosParaReclamosUsuario
   componentDidMount() {
    fetch('http://localhost:8080/apitp/getEdificios')
    .then((res) => res.json()).then((json) => {
       this.setState({
        isLoaded: true,
        edificios: json
      });
    }).catch((error) =>{
      alert("Error en API" + error);
    });
    
    fetch('http://localhost:8080/apitp/getPersonas')
    .then((res) => res.json()).then((json) => {
       this.setState({
        isLoaded: true,
        personas: json
      });
    }).catch((error) =>{
      alert("Error en API" + error);
    });
 }
   handleChange = (event) => {
    var valorSel = event.target.value;

    if(event.target.id === 'listaEdificios'){
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
    else if(event.target.id === 'listaUnidades'){
        console.log(valorSel)
    }
        
};

   handleSubmit(event){
    /*fetch('http://localhost:8080/apitp/agregarDuenioUnidad?codigo=' + codigo) //FALTA ENCONTRAR LA FORMA DE PODER PASAR LOS PARÁMETROS DE LOS VALORES SELECCIONADOS
    .then((res) => res.json()).then((json) => {
       this.setState({
        isLoaded: true,
        reclamos: json,
      });

    }).catch((error) =>{
      alert("Error en API" + error);
    });*/
   };


  render() {
      var  {isLoaded, edificios, unidades: unidadesListadas, personas} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
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
               <select id="listaUnidades" onChange={this.handleChange}>
                  <option value="-1">Seleccione una unidad</option>

                  {
                     unidadesListadas.map(item => (
                     <option value={item.identificador}> {item.piso}° {item.numero}</option>

                     ))
                  } 
               </select>
               <select id="listaPersonas" >
                  <option value="-1">Seleccione una persona</option>

                  {
                     personas.map(item => (
                        <option value={item.documento}>{item.nombre}</option>

                     ))
                  }
               </select>
               <input type="submit" value="Agregar" />
            </form>
         );

      }
   }
}
export default AgregarDueño;
