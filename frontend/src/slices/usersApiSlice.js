import { apiSlice } from "./apiSlice";

function getAuthorizationHeader() {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    return userInfo ? {
        Authorization: `Bearer ${userInfo.accessToken}`,
    } : null;
}

export const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({ url: '/api/auth/authenticate', method: "POST", body: data }),
        }),
        register: builder.mutation({
            query: (data) => ({ url: '/api/auth/register', method: "POST", body: data }),
        }),
        whoAmI: builder.mutation({
            query: () => ({ url: '/api/users/whoami', method: "GET", headers: getAuthorizationHeader() }),
        }),
    }),
});

export const { useLoginMutation, useRegisterMutation, useWhoAmIMutation } = usersApiSlice;