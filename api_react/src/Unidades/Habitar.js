import React, {Component} from 'react';

class Habitar extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        edificios: [],
        unidades: [],
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
               fetch('http://localhost:8080/apitp/habitarUnidad?codigo=' + edificio + '&piso=' + piso + '&numero=' + numero, {
                  method: 'PUT'
                }).then(response => {
                  if (response.status === 200) 
                  {
                    alert("Unidad habitada exitosamente.");
                    window.location = '/';
                  }
                  else if (response.status === 409)
                  {
                      alert("Error al habitar la unidad.");
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

  render() {
      var  {edificios, unidades: unidadesListadas} =this.state;

     
      return (
         <form onSubmit={this.handleSubmit}>
             <div className='container'>
                   <h2>Habitar unidad</h2>
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
            <br></br>

            <input type="submit" value="Liberar" />
            </div>
         </form>
      );

   }
}
export default Habitar;
