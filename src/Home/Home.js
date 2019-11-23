import React from 'react';
import Personas from '../Personas/Personas';
import { cpus } from 'os';


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

const home  = () => {
 return  (
	 
	 <div>
			<h2 align = "center">Log In</h2>
			<form action="javascript:void(1);">
				<h3 align = "center">Inicio de sesión</h3>
				<div className="row">				
					<div className="col-md-2">
						<label>Ingrese su usuario</label>
					</div>
					<div className="col-md-10">
						<input type="text" name="usuario" id="usuario" placeholder="Nombre de usuario"/>
					</div>
				</div>				
				<div className="row">				
					<div className="col-md-2">
						<label>Contraseña</label>
					</div>
					<div className="col-md-10">
						<input type="password" name="password" id="password" placeholder="Contraseña"/>
					</div>
				</div>				
				<button type="button" onClick={chequear}> Iniciar Sesión</button>
	
			</form>
			
		</div>
 );
}

export default home


