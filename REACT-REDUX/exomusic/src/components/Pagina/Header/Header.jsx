import React, { Component } from 'react';
import Logo from 'C:/Users/Tiziano Massa/react-workspace/exomusic/src/logo.png'

class Header extends Component {
  
    render() {
        if (this.props.pagina === 'login' || this.props.pagina === 'registrati') {
            return (
                <div className="bg-dark">
                    <img src={Logo} alt=""/>
                </div>
            );
        }
        return (
            <div className="jumbotron bg-dark text-light">
                <h2 className="display-4">AMMINISTRAZIONE EXOMUSIC</h2>
                <h3 className="lead">Inserimento Album</h3>
                <a href="/" onClick={this.logout}>Logout</a>
            </div>
        );
    }
}

export default Header;

