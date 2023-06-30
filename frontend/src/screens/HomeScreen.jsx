import React from 'react'
import { Row, Col } from 'react-bootstrap'
import Product from '../components/Product'
import axios from 'axios';
import { useGetProductsQuery } from '../slices/productsApiSlice';
import Loader from '../components/Loader';
import Message from '../components/Message';

const HomeScreen = () => {
    const { data: products = [], isLoading, error } = useGetProductsQuery();
    return (
        <>
            {isLoading ? (<Loader />) : error ? (<Message variant={'danger'}>{error?.data?.message || error.error}</Message>) : (<>  <h1>Latest Products</h1>
                <Row>
                    {products.content.map(product => (
                        <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                            <Product product={product} />
                        </Col>
                    ))}
                </Row></>)}


        </>
    )
}



export default HomeScreen