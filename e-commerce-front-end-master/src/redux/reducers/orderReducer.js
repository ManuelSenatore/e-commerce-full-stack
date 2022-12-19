import { SET_ORDER_LIST, REMOVE_TO_ORDER } from "../actions/actions";

const initialeState = {
    orderList : []
}

const orderReducer = (state = initialeState, action) => {
    switch(action.type) {
        case SET_ORDER_LIST : 
        return {
            ...state,
            orderList : [...state.orderList, action.payload]
        };
        case REMOVE_TO_ORDER:
            return{
                ...state,
                    orderList: state.orderList.filter((order,i) => i !== action.payload)
            }
        default:
            return state;
    }
}

export default orderReducer;