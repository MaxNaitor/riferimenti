import { combineReducers } from "redux";
import authReducer from "./authReducer";
import negozioReducer from "./negozioReducer";
import utenteReducer from "./utenteReducer";


const rootReducer = combineReducers({
    utente: utenteReducer,
    negozio: negozioReducer,
    autenticazione: authReducer
})

export default rootReducer;