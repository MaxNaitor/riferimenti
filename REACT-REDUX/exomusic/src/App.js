import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './components/Login/Login';
import Utente from './components/Utente/Utente';
import Footer from './components/Pagina/Footer/Footer';
import React, { Component } from 'react';
import Registrazione from './components/Registrazione/Registrazione';
import Negozio from './components/Negozio/Negozio';
import Admin from './components/Admin/Admin';

class App extends Component {

  render() {
    return (
      <div className="App">
        <Router>
          <Switch>
            <Route path="/" exact component={Login} />
            <Route path="/utente" component={Utente} />
            <Route path="/registrati" component={Registrazione} />
            <Route path="/negozio" component={Negozio} />
            <Route path="/admin" component={Admin}/>
          </Switch>
        </Router>
        <Footer/>
      </div>
    );
  }
}

export default App;
