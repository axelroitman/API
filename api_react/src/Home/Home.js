import React, {Component} from 'react';
import Personas from '../Personas/Personas';
import { cpus } from 'os';

export default class Login extends Component {
	constructor(props) {
	  super(props);
  
	  this.state = {
		usuario: "",
		password: "",
		loginErrors: ""
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
	  const { usuario, password } = this.state;
	  fetch('http://localhost:8080/apitp/getPersonaPorUsuario?usuario=' + usuario + "&password=" + password)
	  .then((res) => res.json()).then((json) => {
			if(json == null)
			{
				alert("Usuario y/o contraseña incorrectos.");
			}	
			else
			{	
				alert("Respuesta exitosa *Debería iniciar sesión*");
				console.log(json);
			}
		 this.setState({
		  persona: json,
		});
  
	  }).catch((error) =>{
		alert("Error en API" + error);
	  });
	  
	  console.log(this.state);
	  event.preventDefault();
	}
  
	render() {
	  return (
		<div>
		  <form onSubmit={this.handleSubmit}>
			<div className="row">
				<div className="col-md-2">
					<label>Usuario</label>
				</div>
				<div className="col-md-10">
					<input type="text" name="usuario" placeholder="Usuario" value={this.state.usuario} onChange={this.handleChange} required />
				</div>
				<div className="col-md-2">
					<label>Contraseña</label>
				</div>
				<div className="col-md-10">
					<input type="password" name="password" placeholder="Contraseña" value={this.state.password} onChange={this.handleChange} required />
				</div>
			</div>
  
  
			<button type="submit">Login</button>
		  </form>
		</div>
	  );
	}
  }
  
function chequear()
{
	var usuario = document.getElementById('usuario');
	var password = document.getElementById('password');
	var persona = null;
	var res = null;
	console.log(usuario.value);
	console.log(password.value);
	fetch('http://localhost:8080/apitp/getPersonaPorUsuario?usuario=' + usuario.value)
	.then((res) => res.json()).then((json) => {
	   this.setState({
		persona: json,
	  });

	}).catch((error) =>{
	  alert("Error en API" + error);
	});

	console.log(persona);
	console.log(res);
	/*if(persona.pass != password.value)
	{
		console.log("Usuario y/o contraseña incorrectos");
	}
	else
	{
		console.log("OK.");
	}*/

    
}
