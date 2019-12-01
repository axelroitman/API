import React from 'react';
import { Link } from 'react-router-dom'

class Header extends React.Component {

  handlerClickItem() {
    sessionStorage.removeItem("usuario");
    sessionStorage.removeItem("administrador");
    sessionStorage.removeItem("documento");

    window.location = '/';  
 }

  render() {

    if(sessionStorage.getItem("usuario"))
    {
      if(sessionStorage.getItem("administrador") === "true")
      {
        return (
          <div className="topBlue">
              <h1>Claimer</h1>
             <ul className="menu" >
                <li  style={{ listStyleType: "none" }}>
                  <Link to="/personas"><h3>Personas</h3></Link>
                </li>
                <li  onClick={this.handlerClickItem.bind()} style={{ listStyleType: "none" }}>
                <Link to=""><h3>Cerrar sesión</h3></Link>
              </li>

              </ul>
              <hr/>
          </div>
         );
      }
      else
      {
        return (
          <div className="topBlue">
             <h1>Claimer</h1>
             <ul className="menu" >
              <li  style={{ listStyleType: "none" }}>
                <Link to="/"><h3>Reclamos</h3></Link>
              </li>
              <li  style={{ listStyleType: "none" }}>
                <Link to="/nuevo-reclamo"><h3>Nuevo reclamo</h3></Link>
              </li>
              <li  style={{ listStyleType: "none" }}>
                <Link to="/unidadesAlquiladas"><h3>Mis Alquileres</h3></Link>
              </li>
              <li  style={{ listStyleType: "none" }}>
                <Link to="/unidadesPropias"><h3>Mis Propiedades</h3></Link>
              </li>

              <li  onClick={this.handlerClickItem.bind()} style={{ listStyleType: "none" }}>
                <Link to=""><h3>Cerrar sesión</h3></Link>
              </li>

            </ul>
                <hr/>
         </div>
         );
      }
    }
    else
    {
      return (
        <div className="topBlue">
           <Link to="/home"><h1>Claimer</h1></Link>
           <ul className="menu" >
         <li  style={{ listStyleType: "none" }}>
           <Link to="/home"><h3>Home</h3></Link>
         </li>
       </ul>
          <hr/>
       </div>
       );
    }
    
  }
}

export default Header
