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
            query: () => ({ url: '/api/users/who-am-i', method: "GET", headers: getAuthorizationHeader() }),
        }),
        updateProfile: builder.mutation({
            query: (data) => ({ url: '/api/users/update-profile', method: "POST", headers: getAuthorizationHeader(), body: data }),
        }),
    }),
});

export const { useLoginMutation, useRegisterMutation, useWhoAmIMutation, useUpdateProfileMutation } = usersApiSlice;