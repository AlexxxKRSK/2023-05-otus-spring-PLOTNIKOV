import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import Router from "./router/Router";
import {store} from "./store/store";
import StoreContext from "./store/StoreContext";

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <StoreContext.Provider value={store}>
      <Router/>
    </StoreContext.Provider>
  </React.StrictMode>
);

reportWebVitals();
