import React from 'react'
import { Row, Col, ListGroup, Image } from 'react-bootstrap'
import { FaTrash } from 'react-icons/fa'
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import Message from '../components/Message';

const CartScreen = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const cart = useSelector(state => state.cart);
    const { cartItems } = cart;

    return (<>
        <Row>
            <Col md={8}>
                <h1 style={{ marginBottom: '20px' }}>Shopping Cart</h1>
                {cartItems.length === 0 ? (
                    <Message variant={'info'}>
                        Your cart is empty <Link to="/">Go Back</Link>
                    </Message>
                ) : (<ListGroup variant='flush'>Items</ListGroup>)}

            </Col>
        </Row>

    </>)
}

export default CartScreen