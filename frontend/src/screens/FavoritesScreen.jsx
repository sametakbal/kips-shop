import React from 'react'
import { Col, Row } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import Product from '../components/Product';

const FavoritesScreen = () => {
    const { favoriteProducts } = useSelector(state => state.favorites);
    return (
        <>
            <Row>
                <h1>My Favorites</h1>
                {favoriteProducts && favoriteProducts.map(product => (
                    <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                        <Product product={product} />
                    </Col>
                ))}
            </Row></>
    )
}

export default FavoritesScreen