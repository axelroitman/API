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

   handleSubmit = (event) => {

      event.preventDefault();

      var edificio = document.getElementById("listaEdificios").value;
      var ubicacion = null;
      var piso = null;
      var numero = null;

      if(edificio != -1)
      {
         var unidad = document.getElementById("listaUnidades").value;
         if(unidad == 0)
         {
            ubicacion = document.getElementById("ubicacion").value;
            unidad = null;
            if(ubicacion == '')
            {
               alert("Debe escribir una ubicación");
            }
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
      
            }

            var descripcion = document.getElementById("descripcion").value;
            if(descripcion == '')
            {
               alert("Debe escribir una descripcion de su problema");
            }
            else
            {
               //FETCH
               fetch('http://localhost:8080/apitp/agregarReclamo?codigo=' + edificio + '&piso=' + piso + '&numero=' + numero + '&documento=' + sessionStorage.getItem("documento") + '&ubicacion=' + ubicacion + '&descripcion=' + descripcion, {
                  method: 'POST' // or 'PUT'
                }).then(response => {
                  console.log(response);
                  if (response.status === 201) 
                  {
                    alert("Reclamo creado.");
                    window.location = '/';
                  }
                  else if (response.status === 409)
                  {
                      alert("Error al crear el reclamo.");
                  }
                  else
                  {
                     alert("Respuesta misteriosa.");
                     console.log(response);
                  }

                })
                .catch(error => {
                  alert("ERROR");
                  console.log("Error.", error);
                });
          
            }
         }
      }
      else
      {
         alert("Debe seleccionar un edificio");
      }
     
   }

  render() {
      var  {isLoaded, edificios, unidadesListadas} =this.state;

      if(!isLoaded) {
         return <div>Loading...</div>
      }
      else
      {
         return (
            <form id="frm-reclamo" onSubmit={this.handleSubmit}>
               <h1>Crear Reclamo</h1>
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
               <textarea name="descripcion" id="descripcion" rows="10" cols="50" placeholder="Descripcion"></textarea>
               <button type="submit">Crear reclamo</button>

            </form>
         );

      }
   }
}
export default ReclamoForm;


