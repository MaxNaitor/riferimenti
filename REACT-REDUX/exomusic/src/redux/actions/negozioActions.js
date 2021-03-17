import AlbumService from "../../services/AlbumService"

export const getCatalogo = (ordine) => {
    return (dispatch) => {
        AlbumService.getAll(ordine).then(res => {
            dispatch({
                type: 'GET_CATALOGO',
                catalogo: res.data
            })
        })
    }
}

export const acquistaAlbum = (id) => {
    return (dispatch) => {
        let utenteAttivo = JSON.parse(sessionStorage.getItem('utente'));
        AlbumService.acquista(id).then(res => {
            utenteAttivo.acquistati.push(res.data)
            sessionStorage.setItem('utente', JSON.stringify(utenteAttivo))
        }).then(
            AlbumService.getAll('inserimento').then(res => {
                dispatch({
                    type: 'GET_CATALOGO',
                    catalogo: res.data
                })
            })
        )
    }
}
