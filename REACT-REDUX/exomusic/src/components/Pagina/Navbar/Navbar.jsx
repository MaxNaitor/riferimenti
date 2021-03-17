import React, { Component } from 'react';
import { connect } from 'react-redux';
import { logout, toUtente } from '../../../redux/actions/utenteActions';
import Logo from 'C:/Users/Tiziano Massa/react-workspace/exomusic/src/logo.png'
import { Redirect } from 'react-router-dom'

/* eslint-disable */

class Navbar extends Component {

    logout = () => {
        this.props.logout();
    }

    navUtente = () => {
        this.props.toUtente();
    }

    redirect = () => {
        if (this.props.redirect !== '') {
            return (
                <Redirect to={this.props.redirect} />
            )
        }
    }

    render() {

        return (
            <React.Fragment>
                {this.redirect()}
                <nav className="navbar navbar-expand-md navbar-dark bg-dark" style={{ position: 'fixed', width: "100%", height: "10%", zIndex: 9998 }}>
                    <div style={{ paddingLeft: "20px", zIndex: 9999 }}>
                        <a href="#" className="navbar-brand" onClick={this.navUtente}> {JSON.parse(sessionStorage.getItem('utente')).nome} </a>
                    </div>
                    <ul className="navbar-nav">
                        <li style={{ zIndex: 9999 }}><a href="/negozio" className="nav-link" >Negozio</a></li>
                        <li style={{ zIndex: 9999 }}><a href="/" className="nav-link" onClick={this.logout}>Logout</a></li>
                    </ul>
                    <div style={{ marginLeft: 0, marginTop: "2%", position: 'fixed', width: "100%", height: "10%" }}><img src={Logo} alt="" style={{ height: 'auto', width: '33%' }} /></div>
                </nav>
            </React.Fragment>
        );
    }
}
const mapStateToProps = (state) => {
    return {
        redirect: state.utente.redirect
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => dispatch(logout()),
        toUtente: () => dispatch(toUtente()),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Navbar);