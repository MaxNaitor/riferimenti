import axios from "axios";

const BASE_REST_URL = "http://localhost:8080/exomusic-WEB/rest/albums";

class AlbumService {

    salva(album) {
        return axios.post(BASE_REST_URL,album);
    }

    getAll(ordine) {
        return axios.get(BASE_REST_URL + '/' + ordine);
    }

    acquista(albumID) {
        let acquisto = {
            idUtente: JSON.parse(sessionStorage.getItem("utente")).id,
            idAlbum: albumID
        }
        return axios.post(BASE_REST_URL + "/acquista",acquisto);
    }

    restituisci(albumID) {
        let restituzione = {
            idUtente: JSON.parse(sessionStorage.getItem("utente")).id,
            idAlbum: albumID
        }
        return axios.post(BASE_REST_URL + "/restituisci",restituzione);
    }
    
}

export default new AlbumService();