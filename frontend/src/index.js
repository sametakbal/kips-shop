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
const CartScreen = React.lazy(() => import('./screens/CartScreen'));
const FavoritesScreen = React.lazy(() => import('./screens/FavoritesScreen'));
const LoginScreen = React.lazy(() => import('./screens/LoginScreen'));
const RegisterScreen = React.lazy(() => import('./screens/RegisterScreen'));
const AccountScreen = React.lazy(() => import('./screens/AccountScreen'));
const ShippingScreen = React.lazy(() => import('./screens/ShippingScreen'));
const PrivateRoute = React.lazy(() => import('./components/PrivateRoute'));

const router = createBrowserRouter(createRoutesFromElements(
  <Route path="/" element={<App />} >
    <Route index={true} path="/" element={<Suspense fallback={<Loader />}><HomeScreen /></Suspense>} />
    <Route path="/product/:id" element={<Suspense fallback={<Loader />}><ProductScreen /></Suspense>} />
    <Route path="/cart" element={<Suspense fallback={<Loader />}><CartScreen /></Suspense>} />
    <Route path="/favorites" element={<Suspense fallback={<Loader />}><FavoritesScreen /></Suspense>} />
    <Route path="/login" element={<Suspense fallback={<Loader />}><LoginScreen /></Suspense>} />
    <Route path="/register" element={<Suspense fallback={<Loader />}><RegisterScreen /></Suspense>} />
    <Route path="" element={<Suspense fallback={<Loader />}><PrivateRoute /></Suspense>} >
      <Route path="/shipping" element={<Suspense fallback={<Loader />}><ShippingScreen /></Suspense>} />
      <Route path="/account" element={<Suspense fallback={<Loader />}><AccountScreen /></Suspense>} />
    </Route>
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
