import UtenteService from "../../services/UtenteService"
import withReactContent from 'sweetalert2-react-content';
import Swal from 'sweetalert2';
import AlbumService from "../../services/AlbumService";

export const registrazione = (utente) => {
    return (dispatch) => {
        UtenteService.registrazione(utente).then(res => {
            UtenteService.accesso(utente).then(res => {
                sessionStorage.setItem("utente", JSON.stringify(res.data));
                dispatch({
                    type: 'LOGIN',
                    utente: res.data
                })
            })
        }).catch(error => {
            withReactContent(Swal).fire({
                title: <div> <p>Errore {error.response.status}</p> <p>{error.response.data}</p> </div>
            })
        });
    }
}

export const login = (utente) => {
    return (dispatch) => {
        UtenteService.accesso(utente).then(res => {
            sessionStorage.setItem("utente", JSON.stringify(res.data));
            dispatch({
                type: 'LOGIN',
                utente: res.data
            })
        })
            .catch(error => {
                withReactContent(Swal).fire({
                    title: <div> <p>Errore {error.response.status}</p> <p>{error.response.data}</p> </div>
                })
            });
    }
}

export const ordinaAlbum = (ordine) => {
    return (dispatch) => {
        UtenteService.getAlbums(ordine).then(res => {
            dispatch({
                type: 'ORDINA_ALBUMS',
                acquistati: res.data
            })
        })
    }
}

export const restituisciAlbum = (id) => {
    return (dispatch) => {
        AlbumService.restituisci(id).then(res => {
            UtenteService.getAlbums('inserimento').then(res => {
                let utenteAttivo = JSON.parse(sessionStorage.getItem('utente'))
                utenteAttivo.acquistati = res.data;
                sessionStorage.setItem('utente', JSON.stringify(utenteAttivo))
                dispatch({
                    type: 'ORDINA_ALBUMS',
                    acquistati: res.data
                })
            })
        })
    }
}

export const logout = () => {
    return (dispatch) => {
        sessionStorage.clear();
        dispatch({
            type: 'LOGOUT'
        })
    }
}

export const toUtente = () => {
    return (dispatch) => {
        UtenteService.accesso(JSON.parse(sessionStorage.getItem('utente'))).then(res => {
            sessionStorage.setItem("utente", JSON.stringify(res.data));
            dispatch({
                type: 'LOGIN',
                utente: res.data
            })
        })
    }
}