import React, { Component } from 'react';
import Header from '../Pagina/Header/Header';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger';
import Traccia from '../Traccia/Traccia';
import AlbumService from '../../services/AlbumService';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

class Admin extends Component {
    state = {
        nome: '',
        artista: '',
        annoUscita: '',
        genere: '',
        copertina: '',
        tracce: [],
    }

    salvaAlbum = () => {
        if (this.validaInput()) {
            AlbumService.salva(this.state).then(res => {
                withReactContent(Swal).fire({
                    title: <p>Album inserito correttamente!</p>
                })
                this.setState({
                    nome: '',
                    artista: '',
                    annoUscita: '',
                    genere: '',
                    copertina: '',
                    tracce: [],
                })
            })
        }
    }

    aggiungiTraccia = (traccia) => {
        let tracceAgg = this.state.tracce;
        tracceAgg.push(traccia);
        this.setState({
            tracce: tracceAgg
        })
    }

    handleChange = (event) => {
        this.setState({
            [event.target.id]: event.target.value
        })
    }

    validaInput = () => {
        if (this.state.nome === '' ||
            this.state.artista === '' ||
            this.state.annoUscita === '' ||
            this.state.genere === '' ||
            this.state.copertina === '') {
            withReactContent(Swal).fire({
                title: <p>Tutti i campi sono obbligatori!</p>
            })
            return false;
        } else if (
            this.state.tracce.length === 0) {
            withReactContent(Swal).fire({
                title: <p>Devi inserire almeno una traccia!</p>
            })
            return false;
        } else if (this.state.annoUscita < 1920 || this.state.annoUscita > new Date().getFullYear()) {
            withReactContent(Swal).fire({
                title: <p>Anno d'uscita non valido!</p>
            })
            return false;
        }
        return true;
    }

    render() {
        return (
            <React.Fragment>
                <Header />
                <div className="row bg-dark text-light rounded" style={{ width: '60%', margin: 'auto', marginTop: '3%' }}>
                    <div className="col-md-6" style={{ width: '37%', marginLeft: 'auto', marginTop: '3%' }}>
                        <label>Nome Album</label><br />
                        <input type="text" id="nome" value={this.state.nome} onChange={this.handleChange} /><br /><br />
                        <label>Artista</label><br />
                        <input type="text" id="artista" value={this.state.artista} onChange={this.handleChange} /><br /><br />
                        <label>Anno di uscita</label><br />
                        <input type="number" id="annoUscita" value={this.state.annoUscita} onChange={this.handleChange} /><br /><br />
                    </div>
                    <div className="col-md-6" style={{ width: '37%', marginRight: 'auto', marginTop: '3%' }}>
                        <label>Genere</label><br />
                        <input type="text" id="genere" value={this.state.genere} onChange={this.handleChange} /><br /><br />
                        <label>URL Copertina</label><br />
                        <input type="text" id="copertina" value={this.state.copertina} onChange={this.handleChange} /><br /><br />
                        <button onClick={this.salvaAlbum} className="btn btn-success" style={{ marginRight: 10 }}>Salva Album</button>
                        <OverlayTrigger trigger="click" placement="right" overlay={<Traccia aggiungi={this.aggiungiTraccia} />}>
                            <button className="btn btn-primary">Inserisci Traccia</button>
                        </OverlayTrigger>
                    </div>
                </div>
            </React.Fragment>
        );
    }
}

export default Admin;