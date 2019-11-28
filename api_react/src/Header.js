import React from 'react';
import { Link } from 'react-router-dom'

class Header extends React.Component {

  handlerClickItem() {
    localStorage.removeItem("usuario");
    localStorage.removeItem("administrador");
    localStorage.removeItem("documento");

    window.location = '/';  
 }

  render() {

    if(localStorage.getItem("usuario"))
    {
      if(localStorage.getItem("administrador") === "true")
      {
        return (
          <div className="topBlue">
              <Link to="/"><h1>Administración de edificios - API Lunes Mañana 2° Cuatrimestre 2019 Grupo 6</h1></Link>
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
             <Link to="/"><h1>Administración de edificios - API Lunes Mañana 2° Cuatrimestre 2019 Grupo 6</h1></Link>
             <ul className="menu" >
              <li  style={{ listStyleType: "none" }}>
                <Link to="/"><h3>Reclamos</h3></Link>
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
           <Link to="/"><h1>Administración de edificios - API Lunes Mañana 2° Cuatrimestre 2019 Grupo 6</h1></Link>
           <ul className="menu" >
         <li  style={{ listStyleType: "none" }}>
           <Link to="/home"><h3>Home</h3></Link>
         </li>
         <li  style={{ listStyleType: "none" }}>
           <Link to="/registro"><h3>Registro</h3></Link>
         </li>
       </ul>
          <hr/>
       </div>
       );
    }
    
  }
}

export default Header