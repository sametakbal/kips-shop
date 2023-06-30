import { apiSlice } from "./apiSlice";
import { PRODUCTS_URL } from "../constants";

export const productsApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        getProducts: builder.query({
            query: () => ({ url: PRODUCTS_URL }),
            keepUnusedDataFor: 5,
        }),
        getProductDetails: builder.query({
            query: (id) => ({ url: `${PRODUCTS_URL}/${id}` }),
            keepUnusedDataFor: 5,
        }),
    }),
});

export const { useGetProductsQuery, useGetProductDetailsQuery } = productsApiSlice;