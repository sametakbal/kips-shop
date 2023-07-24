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
            state.userInfo = { ...action.payload, email: user.sub };
            localStorage.setItem("userInfo", JSON.stringify({ ...action.payload, email: user.sub }));
        },
        logout: (state) => {
            state.userInfo = null;
            localStorage.removeItem("userInfo");
        },
        setUserInfo: (state, action) => {
            state.userInfo = { ...state.userInfo, ...action.payload, };
            localStorage.setItem("userInfo", JSON.stringify({ ...action.payload, ...state.userInfo }));
        }
    }
});

export const { setCredentials, logout, setUserInfo } = authSlice.actions;

export default authSlice.reducer;