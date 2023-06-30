import React from 'react'
import { Link, useParams } from 'react-router-dom'
import { Button, Card, Col, Image, ListGroup, Row } from 'react-bootstrap';
import Rating from '../components/Rating';
import Carousel from 'react-bootstrap/Carousel';
import { useGetProductDetailsQuery } from '../slices/productsApiSlice';
import Loader from '../components/Loader';
import Message from '../components/Message';

const ProductScreen = () => {
    const { id } = useParams();
    const { data: product = {}, isLoading, error } = useGetProductDetailsQuery(id);

    return (
        <>
            <Link to="/" className="btn btn-light my-3">Go Back</Link>
            {isLoading ? (<Loader />) : error ? (<Message variant={'danger'}>{error?.data?.message || error.error}</Message>) : (<>
                <Row>
                    <Col md={5}>
                        <Carousel>
                            {product.images.map(image => (
                                <Carousel.Item key={image.length}>
                                    <Image src={image} className="d-block w-100" alt={product.name} />
                                </Carousel.Item>
                            ))}

                        </Carousel>

                    </Col>
                    <Col md={4}>
                        <ListGroup variant="flush">
                            <ListGroup.Item>
                                <h3>{product.name}</h3>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Rating value={product.rating} text={`${product.numReviews} reviews`} />
                            </ListGroup.Item>
                            <ListGroup.Item>
                                Price: ${product.price}
                            </ListGroup.Item>
                            <ListGroup.Item>
                                Description: {product.description}
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                    <Col md={3}>
                        <Card>
                            <ListGroup variant="flush">
                                <ListGroup.Item>
                                    <Row>
                                        <Col>
                                            Price:
                                        </Col>
                                        <Col>
                                            <strong>${product.price}</strong>
                                        </Col>
                                    </Row>
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <Row>
                                        <Col>
                                            Status:
                                        </Col>
                                        <Col>
                                            {product.countInStock > 0 ? 'In Stock' : 'Out of Stock'}
                                        </Col>
                                    </Row>
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <Button className="btn-block" type="button" disabled={product.countInStock === 0}>
                                        Add to Cart
                                    </Button>
                                </ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </Col>
                </Row>
            </>)}
        </>
    )
}

export default ProductScreen