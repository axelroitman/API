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
            password: "",
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
        var  {documento, usuario, password, privilegio} =this.state;

        event.preventDefault();

        fetch('http://localhost:8080/apitp/modificarPersona?documento=' + documento + '&usuario=' + usuario + '&password=' + password + '&administrador=' + privilegio, {
            method: 'PUT'
          }).then(response => {
            if (response.status === 200) 
            {
              alert("Persona modificada exitosamente.");
              window.location = '/';
            }
            else if (response.status === 409)
            {
                alert("Error al modificar a la persona.");
            }
            else
            {
               alert("Respuesta misteriosa.");
            }

          })
          .catch(error => {
            alert("ERROR");
               });
    }

    handleChange = (event) => {
        var valorSel = event.target.value;
        this.setState({cargado: true});

        if(event.target.id === "listaPersonas"){

            var persona = document.getElementById("listaPersonas").value;
            var documento= null;
            var nombre= null;
            var administrador = null;
            var usuario = null;
            var contraseña=null;
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
                        nombre = pers.nombre;
                        documento= pers.documento;
                        administrador= pers.administrador;
                        contraseña= pers.pass;
                        usuario = pers.usuario;
                    }
                });
                if(usuario === null || usuario===""){
                    usuario="";
                }
                if(contraseña === null || contraseña==="" ){
                 contraseña="";
                 }
                if(persona != 0){
                    this.setState({documento: documento});
                    this.setState({nombre: nombre});
                    this.setState({usuario: usuario});
                    if(administrador == true)
                    {
                        this.setState({privilegio : "administrador"});

                    }
                    else
                    {
                        this.setState({privilegio : "usuario"});

                    }
                    this.setState({password : contraseña});

                }

            }
        }
        else if(event.target.id === "listaPrivilegios"){
            var administrador = event.target.value;
            if(administrador == true)
            {
                this.setState({privilegio : "administrador"});

            }
            else
            {
                this.setState({privilegio : "usuario"});

            }
        }
        else if(event.target.id === "usuario"){
            this.setState({usuario: event.target.value})
        }
        else if(event.target.id === "documento"){
            this.setState({documento: event.target.value})
        }
        else if(event.target.id === "contraseña"){
            this.setState({password: event.target.value})
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
                    <br></br>

                    <input type="text" id="usuario" name="usuario" placeholder="Usuario" value={this.state.usuario} onChange={this.handleChange}  />
                    <input type="password" id="contraseña" name="contraseña" placeholder="Password" value={this.state.password} onChange={this.handleChange}  />
                    <br></br>
                    <input type="text" id="documento" name="documento" placeholder="Documento" value={this.state.documento}  disabled/>

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