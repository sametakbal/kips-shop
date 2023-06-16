import React from 'react'
import { Card } from 'react-bootstrap'
import { ProductType } from '../types/ProductType'
import { Link } from 'react-router-dom'
import Rating from './Rating'
interface ProductProps {
    product: ProductType
}


const Product: React.FC<ProductProps> = ({ product }) => {
    return (
        <>
            <Card className='my-3 p-3 rounded'>
                <Link to={`/product/${product._id}`} >
                    <Card.Img src={product.image} variant='top' />
                </Link>
                <Card.Body>
                    <a href={`/product/${product._id}`} >
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