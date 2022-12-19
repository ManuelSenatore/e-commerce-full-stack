import { configureStore, combineReducers } from "@reduxjs/toolkit";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import { encryptTransform } from "redux-persist-transform-encrypt";
import userReducer from "../reducers/userReducer";
import prodottoReducer from "../reducers/prodottoReducer";
import preferitiReducer from "../reducers/preferitiReducer";
import carrelloReducer from "../reducers/carrelloReducer";
import categoriaReducer from "../reducers/categoriaReducer";
import orderReducer from "../reducers/orderReducer";


const persistConfig = {
  // 3
  key: "root",
  storage,
  transforms: [
    encryptTransform({
      secretKey: "react",
    }),
  ]
};

const mergedReducers = combineReducers({
  user: userReducer,
  prodotto : prodottoReducer,
  preferiti : preferitiReducer,
  carrello : carrelloReducer,
  categoria : categoriaReducer,
  order : orderReducer
});

const persistedReducer = persistReducer(persistConfig, mergedReducers);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

export const persistor = persistStore(store);