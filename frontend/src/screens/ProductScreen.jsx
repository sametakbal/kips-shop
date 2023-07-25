import React, { useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import { Form, Button, Card, Col, Image, ListGroup, Row } from 'react-bootstrap';
import Rating from '../components/Rating';
import Carousel from 'react-bootstrap/Carousel';
import { useGetProductDetailsQuery } from '../slices/productsApiSlice';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { addToCart } from '../slices/cartSlice';
import { useDispatch, useSelector } from 'react-redux';
import { saveToFavorites } from '../slices/favoritesSlice';
import { MdFavorite, MdOutlineFavoriteBorder } from 'react-icons/md';
import { toast } from "react-toastify";

const ProductScreen = () => {
    const { id } = useParams();
    const { data: product = {}, isLoading, error } = useGetProductDetailsQuery(id);
    const [qty, setQty] = useState(1);
    const isFavorite = useSelector(state => state.favorites.favoriteProducts?.find(i => i.id === Number(id)));

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const addToCartHandler = async () => {
        dispatch(addToCart({ ...product, qty }));
        toast.info('Added to cart', { position: "bottom-right", });
        navigate('/cart');
    }

    const addToFavoritesHandler = async () => {
        dispatch(saveToFavorites({ ...product }));
        toast.success('Added to favorites');
    }

    function getBody() {
        if (isLoading) {
            return <Loader />;
        }
        if (error) {
            return <Message variant={'danger'}>{error?.data?.message || error.error}</Message>;
        }

        return getProductDetails();

    }

    function getProductDetails() {
        return (
            <>
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
                                {product.countInStock > 0 && (
                                    <ListGroup.Item>
                                        <Row>
                                            <Col>Qty</Col>
                                            <Col>
                                                <Form.Control as="select" value={qty} onChange={(e) => setQty(Number(e.target.value))}>
                                                    {[...Array(product.countInStock).keys()].map((x) => (
                                                        <option key={x + 1} value={x + 1}>{x + 1}</option>
                                                    ))}
                                                </Form.Control>
                                            </Col>
                                        </Row>
                                    </ListGroup.Item>
                                )}
                                <ListGroup.Item>
                                    <Button className="btn-block" type="button" disabled={product.countInStock === 0} onClick={addToCartHandler}>
                                        Add to Cart
                                    </Button>
                                    {' '}
                                    <Button className="btn-block" variant='danger' type="button" onClick={addToFavoritesHandler}>
                                        {isFavorite ? (<MdFavorite style={{ color: 'white' }} />) : (<MdOutlineFavoriteBorder style={{ color: 'white' }} />)}
                                    </Button>
                                </ListGroup.Item>
                            </ListGroup>
                        </Card>
                    </Col>
                </Row>
            </>);
    }


    return (
        <>
            <Link to="/" className="btn btn-light my-3">Go Back</Link>
            {getBody()}
        </>
    )
}

export default ProductScreen