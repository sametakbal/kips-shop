import React, { Suspense } from 'react';
import ReactDOM from 'react-dom/client';
import './assets/styles/index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './assets/styles/bootstrap.custom.css';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './store';
import Loader from './components/Loader';

const HomeScreen = React.lazy(() => import('./screens/HomeScreen'));
const ProductScreen = React.lazy(() => import('./screens/ProductScreen'));

const router = createBrowserRouter(createRoutesFromElements(
  <Route path="/" element={<App />} >
    <Route index={true} path="/" element={<Suspense fallback={<Loader />}><HomeScreen /></Suspense>} />
    <Route path="/product/:id" element={<Suspense fallback={<Loader />}><ProductScreen /></Suspense>} />
  </Route>
));

const root = ReactDOM.createRoot(
  document.getElementById('root')
);
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>
);


reportWebVitals();
