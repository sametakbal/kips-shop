import React from 'react'
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Rating from './Rating'


const Product = ({ product }) => {
    return (
        <>
            <Card className='my-3 p-3 rounded'>
                <Link to={`/product/${product.id}`} >
                    <Card.Img src={product.images[0]} variant='top' />
                </Link>
                <Card.Body>
                    <a href={`/product/${product.id}`} >
                        <Card.Title as='div' className='product-title'>
                            <strong>{product.name}</strong>
                        </Card.Title>
                    </a>
                    <Card.Text as='div'>
                        <Rating value={product.rating} text={`${product.numReviews} reviews`} />
                    </Card.Text>
                    <Card.Text as='h3'>
                        ${product.price}
                    </Card.Text>
                </Card.Body>
            </Card>
        </>
    );
}

export default Product;