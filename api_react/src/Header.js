import React from 'react';
import { Link } from 'react-router-dom'
import {Bootstrap, Grid, Row, Col} from 'react-bootstrap';

class Header extends React.Component {

  handlerClickItem() {
    sessionStorage.removeItem("usuario");
    sessionStorage.removeItem("administrador");
    sessionStorage.removeItem("documento");

    window.location = '/';  
 }
 handlePageChange() {
  window.location = '/';
  }

  render() {

    if(sessionStorage.getItem("usuario"))
    {
      if(sessionStorage.getItem("administrador") === "true")
      {
        return (
          <div className="topBlue">
              <h1 onClick={this.handlePageChange}>Claimer</h1>
             <div className="menu" >
                  <div className="menuItem menuItemIzq">
                  <h3>Hola {sessionStorage.getItem("nombre")}</h3>
                  </div>
                  <div className="menuItem">
                    <Link to="/"><h3>Panel de administrador</h3></Link>
                  </div>
                  <div className="menuItem menuItemDer" onClick={this.handlerClickItem.bind()} style={{ listStyleType: "none" }}>
                    <Link to=""><h3>Cerrar sesión</h3></Link>
                  </div>

              </div>
              <hr/>
          </div>
         );
      }
      else
      {
        return (
          <div className="topBlue">
              <h1 onClick={this.handlePageChange}>Claimer</h1>
              <div className="menu" >
                  <div className="menuItem menuItemIzq">
                  <h3>Hola {sessionStorage.getItem("nombre")}</h3>
                  </div>
                  <div className="menuItem">
                  <Link to="/"><h3>Reclamos</h3></Link>
                  </div>
                  <div className="menuItem">
                  <Link to="/nuevo-reclamo"><h3>Nuevo reclamo</h3></Link>
                  </div>
                  <div className="menuItem">
                  <Link to="/unidadesAlquiladas"><h3>Mis Alquileres</h3></Link>
                  </div>
                  <div className="menuItem">
                  <Link to="/unidadesPropias"><h3>Mis Propiedades</h3></Link>
                  </div>
                  <div className="menuItem">
                  <Link to="/recEdif"><h3>Reclamos en mis edificios</h3></Link>
                  </div>

                  <div className="menuItem menuItemDer" onClick={this.handlerClickItem.bind()} style={{ listStyleType: "none" }}>
                    <Link to=""><h3>Cerrar sesión</h3></Link>
                  </div>

              </div>
                <hr/>
         </div>
         );
      }
    }
    else
    {
      return (
        <div className="topBlue">
          <h1 onClick={this.handlePageChange}>Claimer</h1>
       </div>
       );
    }
    
  }
}

export default Header
