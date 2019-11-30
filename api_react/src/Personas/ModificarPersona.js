import React, {Component} from 'react';

class ModificarPersona extends Component{
    constructor(props){
        super(props)
        this.state = {
            personas:[],
            nombre:"",
            usuario:"",
            documento:"",
            privilegio: "",
            isLoaded: false,
            cargado:false
        }
    }

    componentDidMount(){
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

    handleSubmit = (event) => {
    }

    handleChange = (event) => {
        var valorSel = event.target.value;
        this.state.cargado= true;

        if(event.target.id === "listaPersonas"){

            this.setState({documento: valorSel});
            var persona = document.getElementById("listaPersonas").value;
            var documento= null;
            var nombre= null;
            var administrador = null;
            var usuario = null;
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
                       administrador= pers.administrador;
                       nombre= pers.nombre;
                       usuario= pers.usuario;
                    }
                });
                if(persona != 0){
                    this.setState({documento: documento});
                    this.setState({nombre: nombre});
                    this.setState({usuario: usuario});
                    this.setState({admin : administrador})
                    console.log(administrador)
                    if(administrador){
                        this.setState({privilegio: "administrador"});
                    }
                    else{
                        this.setState({privilegio: "usuario"});
                    }
                }

            }
        }
        else if(event.target.id ==="listaPrivilegios"){
            this.setState({privilegio: event.target.value})
        }
        else if(event.target.id === "usuario"){
            this.setState({usuario: event.target.value})
        }
        else if(event.target.id === "documento"){
            this.setState({documento: event.target.value})
        }
    }
    
    render(){
        var  {isLoaded, personas, privilegio, cargado} = this.state;

        if(!isLoaded){
            return <div>Cargando...</div>
        }
        else if(cargado === true){
            return(
                <form onSubmit={this.handleSubmit}>
                    <h1>Modificar Persona</h1>
                    <select id="listaPersonas" onChange={this.handleChange}>
                        <option value="-1"> Seleccione a una persona </option>
                        {
                            personas.map(item => (
                                <option value={item.documento}>{item.nombre}</option>
                            ))
                        }
                    </select>
                    <select id="listaPrivilegios" value={this.state.privilegio} onChange={this.handleChange}>
                        <option value="administrador"> Administrador </option>
                        <option value="usuario"> Usuario </option>
                    </select>
                    <input type="text" name="usuario" placeholder="Usuario" value={this.state.usuario} onChange={this.handleChange} required />
                    <br></br>
                    <input type="text" name="documento" placeholder="Documento" value={this.state.documento} onChange={this.handleChange} required />
                    <br></br>

                    <input type="submit" value="Guardar cambios"/>
                </form>
            );
        }
        else{
            return(
                <form onSubmit={this.handleSubmit}>
                    <h1>Modificar Persona</h1>
                    <select id="listaPersonas" onChange={this.handleChange}>
                        <option value="-1"> Seleccione a una persona </option>
                        {
                            personas.map(item => (
                                <option value={item.documento}>{item.nombre}</option>
                            ))
                        }
                    </select>
                    <br></br>
                    <input type="submit" value="Guardar cambios"/>
                </form>
            );
        }
     }

}
export default ModificarPersona;