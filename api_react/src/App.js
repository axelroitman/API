import React from 'react';

import './App.css';
import Header from './Header'
import Footer from './Footer'
import Home from './Home/Home'
import Registro from './Home/Registro'

import Movies from './Movies/Movies'
import Movie from './Movies/Movie'
import Personas from './Personas/Personas'
import Persona from './Personas/Persona'

import { Route, BrowserRouter as Router } from 'react-router-dom'

function App() {

  return (
    <div className="App">
       <Router>
			<Header />
				<Route exact path="/" component={Home} />
				<Route  path="/home" component={Home} />
				<Route  path="/registro" component={Registro} />
				<Route  path="/personas" component={Personas} />
				<Route  path="/persona/:id" component={Persona} />
				<Route path="/movies" component={Movies} />
				<Route path="/movie/:id" component={Movie} />
		</Router>
       <Footer />
    </div>
  );
}

export default App;
