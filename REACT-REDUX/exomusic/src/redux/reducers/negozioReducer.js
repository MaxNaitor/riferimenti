
const initState = {
    catalogo: [],
}

const negozioReducer = (state = initState, action) => {
    switch (action.type) {
        case 'GET_CATALOGO':
            state = {
                catalogo: action.catalogo,
            }
            break;
        default:
    }
    return state;
}

export default negozioReducer;