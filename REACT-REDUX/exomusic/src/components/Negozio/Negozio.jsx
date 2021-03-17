import React, { Component } from 'react';
import { connect } from 'react-redux';
import { getCatalogo } from '../../redux/actions/negozioActions';
import ListaAlbum from '../ListaAlbum/ListaAlbum';
import Navbar from '../Pagina/Navbar/Navbar';
import {acquistaAlbum} from '../../redux/actions/negozioActions'

class Negozio extends Component {

    acquistaAlbum = (id) => {
        this.props.acquistaAlbum(id)
    }

    componentDidMount() {
        this.props.getCatalogo('inserimento');
    }

    ordinaCatalogo = (ordine) => {
        this.props.getCatalogo(ordine);
    }

    render() {
        return (
            <div>
                <Navbar />
                <br />
                <div className="container">
                    <h1 className="rounded bg-dark text-light" style={{ width: '45%', margin: 'auto', marginTop: '6%' }}>Benvenuto nel Negozio!</h1>
                    <br />
                    {/* passo al figlio la funzione per aggiornare lo stato tramite props */}
                    <ListaAlbum albums={this.props.catalogo} negozio={true} ordina={this.ordinaCatalogo} acquisto={this.acquistaAlbum}/>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        catalogo: state.negozio.catalogo,
        aggiornaNegozio: state.negozio.aggiornaNegozio
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        getCatalogo: (ordine) => dispatch(getCatalogo(ordine)),
        acquistaAlbum: (id) => dispatch(acquistaAlbum(id))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Negozio);