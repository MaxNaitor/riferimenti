import React, { Component } from 'react';
import Header from '../Pagina/Header/Header';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import { login } from '../../redux/actions/utenteActions';
import { connect } from 'react-redux';
import GoogleLogin from 'react-google-login';

class Login extends Component {

    state = {
        email: '',
        pass: ''
    }

    accesso = () => {
        if (this.validaInput()) {
            this.props.login(this.state);
        }
    }

    componentDidUpdate() {
        if (this.props.redirect !== '') {
            this.props.history.push(this.props.redirect)
        }
    }  

    handleChange = (event) => {
        this.setState({
            [event.target.id]: event.target.value
        })
    }

    validaInput = () => {
        if (this.state.email === '' || this.state.pass === '') {
            withReactContent(Swal).fire({
                title: <p>Tutti i campi sono obbligatori!</p>
            })
            return false;
        }
        return true;
    }

    render() {
        const responseGoogle = (response) => {
            let utente = {
                email: response.profileObj.email,
                pass: response.profileObj.googleId
            }
            this.props.login(utente);
          
        }
        return (
            <React.Fragment>
                <Header pagina='login' />
                <div className="card bg-dark text-light" style={{ width: '30%', margin: 'auto', marginTop: '3%' }}>
                    <h1>Accedi</h1>
                    <label>Email</label>
                    <input type="text" id="email" onChange={this.handleChange} style={{ width: '50%', margin: 'auto' }} /><br />
                    <label>Password</label>
                    <input type="password" id="pass" onChange={this.handleChange} style={{ width: '50%', margin: 'auto' }} /><br />
                    <button type="submit" className="btn btn-primary" onClick={this.accesso} style={{ width: '50%', margin: 'auto' }}>Accedi</button> <br />
                    <a href="/registrati">Registrati</a> <br />
                    <a href="/admin">Accedi come Amministratore</a>
                    <br></br>
                    <GoogleLogin
                            clientId="707958371715-037kvj7bcvamv71qe46i0autpj0ej9k4.apps.googleusercontent.com"
                            buttonText="Login"
                            onSuccess={responseGoogle}
                            onFailure={responseGoogle}
                            cookiePolicy={'single_host_origin'}
                        />
                </div>
            </React.Fragment>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        utente: state.utente.utente,
        redirect: state.utente.redirect
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        login: (utente) => dispatch(login(utente))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);