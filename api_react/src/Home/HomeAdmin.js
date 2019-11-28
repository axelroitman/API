import React, {Component} from 'react';

import { Link } from 'react-router-dom'


export default class InicioAdmin extends Component {
	constructor(props) {
	  super(props);
	}
  
    render(){ 
        return( 
          <ul>
            <li  style={{ listStyleType: "none" }}>
                  <Link to="/recPorPers"><h3>Ver reclamos por persona</h3></Link>
                </li>
            <li  style={{ listStyleType: "none" }}>
              <Link to="/recPorEd"><h3>Ver reclamos por edificio</h3></Link>
            </li>  
            <li  style={{ listStyleType: "none" }}>
              <Link to="/verReclamos"><h3>Todos los reclamos</h3></Link>
            </li>    
            <li  style={{ listStyleType: "none" }}>
              <Link to="/unidades"><h3>Ver unidades</h3></Link>
            </li>
            <li  style={{ listStyleType: "none" }}>
              <Link to="/edificios"><h3>Ver edificios</h3></Link>
            </li>  
            <li  style={{ listStyleType: "none" }}>
              <Link to="/habilitadosPorEdificio"><h3>Ver habilitados por edificio</h3></Link>
            </li>   
            <li  style={{ listStyleType: "none" }}>
              <Link to="/habitantesPorEdificio"><h3>Ver habitantes por edificio</h3></Link>
            </li>   
            <li  style={{ listStyleType: "none" }}>
              <Link to="/dueñosPorEdificio"><h3>Ver dueños por edificio</h3></Link>
            </li> 
            <li  style={{ listStyleType: "none" }}>
              <Link to="/inquilinosPorUnidad"><h3>Ver inquilinos por unidad</h3></Link>
            </li>
            <li  style={{ listStyleType: "none" }}>
              <Link to="/transferir"><h3>Transferir unidad</h3></Link>
            </li>
            <li  style={{ listStyleType: "none" }}>
              <Link to="/agregarDueño"><h3>Agregar dueño</h3></Link>
            </li>
            <li  style={{ listStyleType: "none" }}>
              <Link to="/alquilarUnidad"><h3>Alquilar unidad</h3></Link>
            </li>

          </ul> 
        ) 
      }
    }