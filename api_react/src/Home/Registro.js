import React, { Component } from "react";

export default class Registro extends Component {
  constructor(props) {
    super(props);

    this.state = {
      nombre: "",
      apellido: "",
      documento: "",
      usuario: "",
      password: "",
      password_confirmation: "",
      registrationErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const { nombre, apellido, documento, usuario, password, password_confirmation} = this.state;
  /*var url = 'http://localhost:8080/apitp/agregarPersona';
    var data = {documento: documento, nombre: apellido + ', ' + nombre, usuario: usuario, password: password};
    */
    if(password === password_confirmation){
     /* fetch(url, {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      })*/
      
      
      fetch('http://localhost:8080/apitp/agregarPersona?documento=' + documento + '&nombre=' + nombre + '&apellido=' + apellido + '&usuario=' + usuario + '&password=' + password, {
        method: 'POST' // or 'PUT'
      }).then(response => {
        console.log(response);
        if (response.status === 201) 
        {
          alert("Usuario creado exitosamente.");
          window.location = "/";    
          }
        else if (response.status === 409)
        {
            alert("Error al crear el usuario.");
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();

    }
    else
    {
      alert("Las contraseñas no coinciden");
    }
 
  }

  render() {
    return (
  <div className="container">
    <div>
      <h2>Registro</h2>

      <form onSubmit={this.handleSubmit}>
       
       <div className="row">
          <div className="col-md-2">
						<label>Nombre</label>
					</div>
          <div className="col-md-10">
            <input type="nombre"name="nombre"placeholder="Nombre"value={this.state.nombre}onChange={this.handleChange}required/>  
          </div>
          <div className="col-md-2">
						<label>Apellido</label>
					</div>
          <div className="col-md-10">
            <input type="apellido"name="apellido"placeholder="Apellido"value={this.state.apellido}onChange={this.handleChange}required/>
          </div> 
          <div className="col-md-2">
						<label>Documento</label>
					</div>
          <div className="col-md-10">
            <input type="documento"name="documento"placeholder="Documento"value={this.state.documento}onChange={this.handleChange}required/>
          </div>
          <div className="col-md-2">
						<label>Usuario</label>
					</div>
          <div className="col-md-10">
            <input type="usuario"name="usuario"placeholder="Usuario"value={this.state.usuario}onChange={this.handleChange}required/>
          </div>
          <div className="col-md-2">
						<label>Contraseña</label>
					</div>
          <div className="col-md-10">  
            <input type="password"name="password" placeholder="Contraseña" value={this.state.password} onChange={this.handleChange}required />
          </div> 
          <div className="col-md-2">
						<label>Repita contraseña</label>
					</div>
          <div className="col-md-10">
            <input type="password" name="password_confirmation" placeholder="Repita su contraseña"value={this.state.password_confirmation} onChange={this.handleChange}required/>
          </div> 
        </div>
        <div className="row divBotones">
          <button type="submit">Registrarse</button>
        </div>

      </form>
    </div>
  </div>
    );
  }
}