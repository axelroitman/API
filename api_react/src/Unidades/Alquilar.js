import React, {Component} from 'react';
import { Link } from 'react-router-dom'

class Alquilar extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        edificios: [],
        unidades: [],
        personas: [],
        isLoaded:false
      }
      this.handleSubmit = this.handleSubmit.bind(this);
	  this.handleChange = this.handleChange.bind(this);
   }
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
        
};

handleSubmit = (event) => {
      event.preventDefault();

      var edificio = document.getElementById("listaEdificios").value;
      var documento = null;
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
      
            }
         var persona = document.getElementById("listaPersonas").value;
         if(persona == 0)
         {
            persona = null;
         }

         if(persona == -1)
         {
            alert("Debe seleccionar una persona");
         }
         else
         {
            if(persona != 0)
            {
               documento = persona;
               console.log(documento)
            }
               fetch('http://localhost:8080/apitp/alquilarUnidad?codigo=' + edificio + '&piso=' + piso + '&numero=' + numero + '&documento=' + documento, {
                  method: 'POST'
                }).then(response => {
                  if (response.status === 200) 
                  {
                    alert("Se ha alquilado la unidad");
                    window.location = '/';
                  }
                  else if (response.status === 409)
                  {
                      alert("Error al alquilar.");
                  }
                  else
                  {
                     alert("Respuesta misteriosa.");
                  }
   
                })
                .catch(error => {
                  alert("ERROR");
                  console.log("Error.", error);
                     });
                  }
            }
            
            
         }
      }

  render() {
      var  {isLoaded, edificios, unidades: unidadesListadas, personas: personasListadas} =this.state;

      if(!isLoaded) {
         return (
            <div className='container'>
            <h2>Alquilar unidad</h2>
            Cargando...
            </div>
         )
      }
      else
      {
         return (
            <form onSubmit={this.handleSubmit}>
                <div className='container'>
                   <h2>Alquilar unidad</h2>
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
                     unidadesListadas.map(item => (
                     <option value={item.identificador}> {item.piso}° {item.numero}</option>

                     ))
                  } 
               </select>
               <br></br>

               <select id="listaPersonas" >
                  <option value="-1">Seleccione una persona</option>

                  {
                     personasListadas.map(item => (
                        <option value={item.documento}>{item.nombre}</option>

                     ))
                  }
               </select>
               <br></br>
               <br></br>

               <input type="submit" value="Alquilar" />
               </div>
            </form>
         );

      }
   }
}
export default Alquilar;