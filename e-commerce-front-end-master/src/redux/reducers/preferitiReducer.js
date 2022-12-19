import { SET_PREFERITI_LIST } from "../actions/actions";

const initialeState = {
    preferitiList : []
}

const preferitiReducer = (state = initialeState, action) => {
    switch(action.type) {
        case SET_PREFERITI_LIST : 
        return {
            ...state,
            preferitiList : action.payload
        };
        default:
            return state;
    }
}

export default preferitiReducer;