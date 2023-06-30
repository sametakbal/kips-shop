import { createApi } from "@reduxjs/toolkit/dist/query/react";
import { PRODUCTS_URL } from "../constants";

export const productsApiSlice = createApi({
    endpoints: (builder) => ({
        getProducts: builder.query({
            query: () => ({ url: PRODUCTS_URL }),
            keepUnusedDataFor: 5,
        }),
    }),
});

export const { useGetProductsQuery } = productsApiSlice;