import React from 'react';
import { Link } from 'react-router-dom'

class Header extends React.Component {
  render() {
    return (
     <div className="topBlue">
        <h1>Administración de edificios - API Lunes Mañana 2° Cuatrimestre 2019 Grupo 6</h1>
        <ul className="menu" >
			<li  style={{ listStyleType: "none" }}>
				<Link to="/home"><h3>Home</h3></Link>
			</li>
      <li  style={{ listStyleType: "none" }}>
				<Link to="/registro"><h3>Registro</h3></Link>
			</li>

            <li  style={{ listStyleType: "none" }}>
				<Link to="/movies"><h3>Movies</h3></Link>
			</li>
      <li  style={{ listStyleType: "none" }}>
				<Link to="/personas"><h3>Personas</h3></Link>
			</li>

		</ul>
       <hr/>
    </div>
    );
  }
}

export default Header