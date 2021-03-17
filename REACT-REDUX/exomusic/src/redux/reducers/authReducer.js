

const initState = {
    registrazione: {
        nome: '',
        cognome: '',
        sesso: '',
        email: '',
        pass: '',
        dataNascita: ''
    },
    login: {
        email: '',
        pass: '',
    }
}

const authReducer = (state = initState, action) => {
    return state;
}

export default authReducer