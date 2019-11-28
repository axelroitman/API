import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class ReclamoForm extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        reclamo: {},
        edificios: [],
        unidadesListadas: [],
        isLoaded:false
      }
   }
  // getEdificiosParaReclamosUsuario
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
   handleChange = (event) => {
      var valorSel = event.target.value;
      if(event.target.id == "listaEdificios")
      {
         if(valorSel == -1)
         {
            document.getElementById("espacioComun").style.display = "none";
         }
         else
         {
            document.getElementById("espacioComun").style.display = "block";

         }

         var unidadesMostrar = [];
         this.state.unidades.forEach(function(unidad){
            if(unidad.edificio.codigo == valorSel)
            {
               unidadesMostrar.push(unidad);
            }
         });
         this.setState({
            unidadesListadas: unidadesMostrar
            });   
      }
      else if(event.target.id == "listaUnidades")
      {
         if(valorSel == 0)
         {
            document.getElementById("ubicacion").style.display = "inline";           
         }
         else
         {
            document.getElementById("ubicacion").style.display = "none";          
         }
      }
      

      //Busco las unidades y las muestro.
      //this.setState({ value: event.target.value });
   };


  render() {
      var  {isLoaded, edificios, unidadesListadas} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
         return (
            <form>
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
                  <option value="0" id="espacioComun" style={{display:'none'}}>Espacio común</option> 
                  {
                     unidadesListadas.map(item => (
                     <option value={item.identificador}> {item.piso}° {item.numero}</option>

                     ))
                  } 
               </select>
               <input style={{display: 'none'}} type="text" id="ubicacion" name="ubicacion" placeholder="Ubicacion"/>

            </form>
         );

      }
   }
}
export default ReclamoForm;


