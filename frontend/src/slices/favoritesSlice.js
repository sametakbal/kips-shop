import { createSlice } from '@reduxjs/toolkit';

const initialState = localStorage.getItem('favorites')
    ? JSON.parse(localStorage.getItem('favorites'))
    : { favoriteProducts: [] };

const favoritesSlice = createSlice({
    name: 'favorites',
    initialState,
    reducers: {
        saveToFavorites: (state, action) => {
            const product = action.payload;
            const existProduct = state.favoriteProducts.find((x) => x.id === product.id);
            if (existProduct) {
                state.favoriteProducts = state.favoriteProducts.filter((x) => x.id !== product.id);
            } else {
                state.favoriteProducts = [...state.favoriteProducts, product];
            }
            localStorage.setItem('favorites', JSON.stringify(state));
        }
    }
});

export const { saveToFavorites } = favoritesSlice.actions;

export default favoritesSlice.reducer;