import { apiSlice } from "./apiSlice";

export const usersApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({ url: '/api/auth/authenticate', method: "POST", body: data }),
        }),
    }),
});

export const { useLoginMutation } = usersApiSlice;