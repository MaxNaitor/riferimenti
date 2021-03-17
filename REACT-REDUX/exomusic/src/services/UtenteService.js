import axios from "axios";

const BASE_REST_URL = "http://localhost:8080/exomusic-WEB/rest/utenti";

class UtenteService {

    accesso(utenteInput) {
        return axios.post(BASE_REST_URL + '/accesso',utenteInput);
    }

    registrazione(utenteInput) {
        return axios.post(BASE_REST_URL + '/registrazione',utenteInput);
    }

    getAlbums(ordine) {
        return axios.get(BASE_REST_URL + '/' + JSON.parse(sessionStorage.getItem('utente')).id + '/' + ordine);
    }
}

export default new UtenteService();