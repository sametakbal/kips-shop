import { apiSlice } from "./apiSlice";

export const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({ url: '/api/auth/authenticate', method: "POST", body: data }),
        }),
        register: builder.mutation({
            query: (data) => ({ url: '/api/auth/register', method: "POST", body: data }),
        }),
    }),
});

export const { useLoginMutation, useRegisterMutation } = usersApiSlice;