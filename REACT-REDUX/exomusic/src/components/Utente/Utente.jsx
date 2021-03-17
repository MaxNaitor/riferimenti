import React, { Component } from 'react';
import Navbar from '../Pagina/Navbar/Navbar';
import { connect } from 'react-redux'
import { ordinaAlbum, restituisciAlbum } from '../../redux/actions/utenteActions';
import ListaAlbum from '../ListaAlbum/ListaAlbum';


class Utente extends Component {

    navigaNegozio = () => {
        this.props.history.push("/negozio");
        this.props.history.go();
    }

    ordinaAcquistati = (ordine) => {
        this.props.ordinaAlbum(ordine)
    }

    restituisciAlbum = (id) => {
        this.props.restituisciAlbum(id)
    }

    renderListaAlbum = () => {
        if (this.props.acquistati.length === 0) {
            return (
                <React.Fragment>
                    <br /><br />
                    <h1>Oh No!</h1>
                    <h3>Non hai album nella tua collezione!</h3> <br />
                    <button className="btn btn-danger" onClick={this.navigaNegozio}>Vai al Negozio!</button>
                    <br /><br /><br />
                </React.Fragment>
            )
        } else {
            return <ListaAlbum albums={this.props.acquistati} ordina={this.ordinaAcquistati} restituzione={this.restituisciAlbum} />
        }
    }

    render() {
        return (
            <div>
                <Navbar />
                <br />
                <div className="container">
                    <h1 className="rounded bg-dark text-light" style={{ width: '30%', margin: 'auto', marginTop: '6%' }}>Ciao {this.props.utente.nome}!</h1>
                    <br />
                    <div className="row">
                        <div className="col-2 bg-dark text-light" style={{ position: "fixed", width: "22%", marginRight: '30%' }}>
                            <h1>I Tuoi Dati</h1>
                            <br></br>
                            <h3>{this.props.utente.nome} {this.props.utente.cognome}</h3>
                            <p>Data di nascita: {this.props.utente.dataNascita}</p>
                            <p>Email: {this.props.utente.email}</p>
                            <p>Album acquistati: {this.props.acquistati.length}</p>
                        </div>
                        <div className="col-3"></div>
                        <div className="col-sm-9 text-light rounded">
                            {this.renderListaAlbum()}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        utente: state.utente.utente,
        acquistati: state.utente.acquistati
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        ordinaAlbum: (ordine) => dispatch(ordinaAlbum(ordine)),
        restituisciAlbum: (id) => dispatch(restituisciAlbum(id))
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Utente);