import { SET_CARRELLO_LIST } from "../actions/actions";

const initialeState = {
    carrelloList : []
}

const carrelloReducer = (state = initialeState, action) => {
    switch(action.type) {
        case SET_CARRELLO_LIST : 
        return {
            ...state,
            carrelloList : action.payload
        };
        default:
            return state;
    }
}

export default carrelloReducer;