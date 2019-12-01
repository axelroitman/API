import React, {Component} from 'react';

class EliminarPersona extends Component{
    constructor(props){
        super(props)
        this.state = {
            personas: [],
            documento: "",
            isLoaded: false
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/apitp/getPersonas')
        .then((res) => res.json()).then((json) => {
  
           this.setState({
            personas: json,
            isLoaded: true
          });
        }).catch((error) =>{
          alert("Error en API" + error);
        });
    }

    handleSubmit = (event) => {
        event.preventDefault();

        fetch('http://localhost:8080/apitp/eliminarPersona?documento=' + this.state.documento, {
            method: 'DELETE'
          }).then(response => {
            if (response.status === 200) 
            {
              alert("Persona eliminada exitosamente.");
              window.location = '/';
            }
            else if (response.status === 409)
            {
                alert("Error al eliminar a la persona. Verifique que esta no esta no sea dueño o inquilino de una unidad.");
            }

          })
          .catch(error => {
              console.log(error)
            alert("ERROR");
               });
    }

    handleChange = (event) =>  {
        if(event.target.id === "listaPersonas"){

            var persona = document.getElementById("listaPersonas").value;
            var documento= null;
            if(persona == 0)
            {
               persona = null;
            }
   
            if(persona == -1)
            {
               alert("Debe seleccionar una persona");
            }
            else{
                this.state.personas.forEach(function(pers){
                    if(pers.documento === persona)
                    {
                        documento= pers.documento;
                    }
                });
                if(persona != 0){
                    this.setState({documento : documento});
                }

            }
        }
    }

    render(){
        var  {isLoaded, personas} =this.state;

        if(isLoaded === false){
            return <div>Cargando...</div>
        }
        else{
            return(
            <form onSubmit={this.handleSubmit}>
                    <h1>Eliminar Persona</h1>
                    <select id="listaPersonas" onChange={this.handleChange}>
                        <option value="-1"> Seleccione a una persona </option>
                        {
                            personas.map(item => (
                                <option value={item.documento}>{item.nombre}</option>
                            ))
                        }
                    </select>
                        <br/>
                        <br/>
                    <input type="submit" value="Eliminar persona"/>
                </form>
            )
        }
    }
}

export default EliminarPersona;