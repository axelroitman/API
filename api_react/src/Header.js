import React from 'react';
import { Link } from 'react-router-dom'

class Header extends React.Component {
  render() {
    return (
     <div className="topBlue">
        <h1>UADE - Aplicaciones Distribuidas 2019</h1>
        <ul className="menu" >
			<li  style={{ listStyleType: "none" }}>
				<Link to="/home"><h3>Home</h3></Link>
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