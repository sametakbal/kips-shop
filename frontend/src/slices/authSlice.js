import { createSlice } from "@reduxjs/toolkit";
import jwt from 'jwt-decode';
const initialState = {
    userInfo: localStorage.getItem("userInfo") ? JSON.parse(localStorage.getItem("userInfo")) : null,
};

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        setCredentials: (state, action) => {
            let user = jwt(action.payload.accessToken);
            state.userInfo = { ...action.payload, name: user.sub };
            localStorage.setItem("userInfo", JSON.stringify({ ...action.payload, name: user.sub }));
        },
        logout: (state) => {
            state.userInfo = null;
            localStorage.removeItem("userInfo");
        }
    }
});

export const { setCredentials, logout } = authSlice.actions;

export default authSlice.reducer;