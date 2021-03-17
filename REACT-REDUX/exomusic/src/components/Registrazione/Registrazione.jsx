import React, { Component } from 'react';
import Header from '../Pagina/Header/Header';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';
import { connect } from 'react-redux';
import { registrazione } from '../../redux/actions/utenteActions';
import GoogleLogin from 'react-google-login';

class Registrazione extends Component {

    state = {
        nome: '',
        cognome: '',
        sesso: '',
        email: '',
        pass: '',
        dataNascita: ''
    }

    registrazione = () => {
        if (this.validaInput()) {
            this.props.registrazione(this.state);
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
        if (this.state.nome === '' || this.state.cognome === ''
            || this.state.dataNascita === '' || this.state.sesso === ''
            || this.state.email === '' || this.state.pass === '') {
            withReactContent(Swal).fire({
                title: <p>Tutti i campi sono obbligatori!</p>
            })
            return false;
        }
        return true;
    }

    render() {
        const responseGoogle = (response) => {
            this.setState({
                nome: response.profileObj.givenName,
                cognome: response.profileObj.familyName,
                email: response.profileObj.email,
                pass: response.profileObj.googleId,
                dataNascita: '2021-03-06',
                sesso: 'N'
            })
         
            this.registrazione()
        }
        return (
            <React.Fragment>
                <Header pagina="registrati" />
                <div className="row bg-dark text-light rounded" style={{ width: '60%', margin: 'auto', marginTop: '3%' }}>
                    <h1>Registrati</h1>
                    <div className="col-md-6" style={{ width: '30%', marginLeft: 'auto', marginTop: '3%' }}>

                        <label>Nome</label><br />
                        <input type="text" id="nome" onChange={this.handleChange} /><br /><br />
                        <label>Cognome</label><br />
                        <input type="text" id="cognome" onChange={this.handleChange} /><br /><br />
                        <label>Sesso</label><br />
                        <div onChange={this.handleChange}>
                            <input type="radio" id="sesso" name="sesso" value="M" /><span> M </span>
                            <input type="radio" id="sesso" name="sesso" value="F" /><span> F</span>
                        </div><br />
                    </div>
                    <div className="col-md-6" style={{ width: '30%', marginRight: 'auto', marginTop: '3%' }}>
                        <label>Email</label><br />
                        <input type="text" id="email" onChange={this.handleChange} /><br /><br />
                        <label>Password</label><br />
                        <input type="password" id="pass" onChange={this.handleChange} /><br /><br />
                        <label>Data di Nascita</label><br />
                        <input type="date" id="dataNascita" onChange={this.handleChange} /><br /><br />
                    </div>
                    <div className="bg-dark text-light" style={{ margin: 'auto', width: '50%' }}>
                        <button type="submit" className="btn btn-primary" onClick={this.registrazione}>Registrati</button> <br /><br />
                        <script type="text/javascript">

                        </script>
                        <a href="/">Accedi</a> <br />
                        <a href="/admin">Accedi come Amministratore</a>
                        <br /><br />
                        <GoogleLogin
                            clientId="707958371715-037kvj7bcvamv71qe46i0autpj0ej9k4.apps.googleusercontent.com"
                            buttonText="Login"
                            onSuccess={responseGoogle}
                            onFailure={responseGoogle}
                            cookiePolicy={'single_host_origin'}
                        />
                    </div>
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
        registrazione: (utente) => dispatch(registrazione(utente))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Registrazione);