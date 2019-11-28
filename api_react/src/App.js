import React from 'react';

import './App.css';
import Header from './Header'
import Footer from './Footer'
import Home from './Home/Home'
import HomeAdmin from './Home/HomeAdmin'

import Registro from './Home/Registro'

import Reclamos from './Reclamos/Reclamos'
import Reclamo from './Reclamos/Reclamo'
import ReclamoForm from './Reclamos/ReclamoForm'
import Personas from './Personas/Personas'
import Persona from './Personas/Persona'
import Edificios from './Edificios/Edificios'
import Habitantes from './Edificios/Habitantes'
import ReclamosPorEdificio from './Edificios/ReclamosPorEdificio'


import { Route, BrowserRouter as Router } from 'react-router-dom'
import TodosLosReclamos from './Reclamos/TodosLosReclamos';

function App() {

  return (
    <div className="App">
       <Router>
			<Header />
				<Route exact path="/" component={Home} />
				<Route  path="/home" component={Home} />
				<Route  path="/homeAdmin" component={HomeAdmin} />
				<Route  path="/registro" component={Registro} />
				<Route  path="/personas" component={Personas} />
				<Route  path="/persona/:id" component={Persona} />
				<Route  path="/reclamos" component={Reclamos} />

				<Route  path="/habitantesPorEdificio" component={Habitantes} />
				<Route  path="/recPorEd" component={ReclamosPorEdificio} />
				<Route  path="/verReclamos" component={TodosLosReclamos} />

				<Route  path="/reclamo/:id" component={Reclamo} />
				<Route  path="/nuevo-reclamo" component={ReclamoForm} />
				<Route  path="/edificios" component={Edificios} />

		</Router>
       <Footer />
    </div>
  );
}

export default App;
