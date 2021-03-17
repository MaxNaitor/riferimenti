import React, { Component } from 'react';
import ListaTracce from '../ListaTracce/ListaTracce';
import OverlayTrigger from 'react-bootstrap/OverlayTrigger';

class ListaAlbum extends Component {

    isNuovo = (album) => {
        if (album.nuovo) {
            return (
                <div style={{ zIndex: 999, position: 'absolute' }}>
                    <h5 className="rounded bg-danger text-light">Nuovo!</h5>
                </div>
            )
        }
    }

    returnTableHead() {
        if (this.props.negozio) {
            return (
                <React.Fragment>
                    <th className="align-middle">Acquista</th>
                </React.Fragment>
            )
        } else {
            return (
                <React.Fragment>
                    <th className="align-middle">Restituzione</th>
                </React.Fragment>
            )
        }
    }

    returnTableBody(album) {
        let utente = JSON.parse(sessionStorage.getItem("utente"));
        if (this.props.negozio) {
            for (let i = 0; i < utente.acquistati.length; i++) {
                //se l'album i-esimo del negozio è presente nella lista di album dell'utente,lo imposto come acquistato
                if (utente.acquistati[i].id === album.id) {
                    return (
                        <React.Fragment>
                            <td className="align-middle">Già Acquistato</td>
                        </React.Fragment>
                    )
                }
            }
            return (
                <React.Fragment>
                    <td className="align-middle"><button onClick={() => this.props.acquisto(album.id)} className="btn btn-success">Acquista</button></td>
                </React.Fragment>
            )
        } else {
            for (let i = 0; i < utente.acquistati.length; i++) {
                //se l'album i-esimo del negozio è presente nella lista di album dell'utente,controllo se è possibile restituirlo
                if (utente.acquistati[i].id === album.id) {
                    if (utente.acquistati[i].restituibile) {
                        return (
                            <React.Fragment>
                                <td className="align-middle"><button onClick={() => this.props.restituzione(album.id)} className="btn btn-danger">Restituisci</button></td>
                            </React.Fragment>
                        )
                    } else {
                        return (
                            <React.Fragment>
                                <td className="align-middle">Non Idoneo</td>
                            </React.Fragment>
                        )
                    }
                }
            }
        }
    }

    pulsantieraOrdinamento() {
        return (
            <React.Fragment>
                <th><button onClick={() => this.props.ordina('inserimento')} className="btn btn-danger">Novità</button></th>
                <th><button onClick={() => this.props.ordina('nome')} className="btn btn-danger">Nome</button></th>
                <th><button onClick={() => this.props.ordina('artista')} className="btn btn-danger">Artista</button></th>
                <th><button onClick={() => this.props.ordina('genere')} className="btn btn-danger">Genere</button></th>
                <th><button onClick={() => this.props.ordina('uscita')} className="btn btn-danger">Uscita</button></th>
            </React.Fragment>
        )
    }

    render() {
        return (
            <React.Fragment>
                <table className="table table-striped table-bordered table-dark">
                    <thead>
                        <tr>
                            {/* this.props.ordina() è un metodo del componente padre,che si usa dal figlio per modificarne lo stato */}
                            {this.pulsantieraOrdinamento()}
                            {this.returnTableHead()}
                            <th className="align-middle">Tracce</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.props.albums.map(album =>
                            <tr key={album.id}>
                                <td>{this.isNuovo(album)}<img className="copertina" src={album.copertina} alt="" /></td>
                                <td className="align-middle">{album.nome}</td>
                                <td className="align-middle">{album.artista}</td>
                                <td className="align-middle">{album.genere}</td>
                                <td className="align-middle">{album.annoUscita}</td>
                                {this.returnTableBody(album)}
                                <td className="align-middle">
                                    <OverlayTrigger trigger="click" placement="left" overlay={ListaTracce(album.tracce)}>
                                        <button className="btn btn-info text-light">Visualizza</button>
                                    </OverlayTrigger>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </React.Fragment >
        );
    }
}

export default ListaAlbum;