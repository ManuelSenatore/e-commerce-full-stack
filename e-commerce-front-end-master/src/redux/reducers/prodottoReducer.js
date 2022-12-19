import { SET_PRODOTTO_LIST } from "../actions/actions";

const initialState = {
    prodottoList : []
}

const prodottoReducer = (state = initialState, action) => {
    switch(action.type) {
        case SET_PRODOTTO_LIST : 
        return {
            ...state,
            prodottoList : action.payload
        };
        default:
            return state;
    }
}

export default prodottoReducer;