
const initState = {
    utente: {},
    acquistati: [],
    redirect: ''
}

const utenteReducer = (state = initState, action) => {
    switch (action.type) {
        case 'LOGIN':
            state = {
                utente: JSON.parse(sessionStorage.getItem('utente')),
                acquistati: JSON.parse(sessionStorage.getItem('utente')).acquistati,
                redirect: '/utente'
            }
            break;
        case 'ORDINA_ALBUMS':
            state = {
                utente: JSON.parse(sessionStorage.getItem('utente')),
                acquistati: action.acquistati,
                redirect: '/utente'
            }
            break;

        default:
    }
    return state;
}

export default utenteReducer;

