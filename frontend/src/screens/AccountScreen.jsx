import React, { useEffect, useState } from 'react'
import { Col, Row, Nav, Form, Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap'
import FormContainer from '../components/FormContainer';
import { useDispatch, useSelector } from 'react-redux';
import Loader from '../components/Loader';
import { useUpdateProfileMutation } from '../slices/usersApiSlice';
import { toast } from 'react-toastify';
import { setUserInfo } from '../slices/authSlice';


const AccountScreen = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const { userInfo } = useSelector(state => state.auth);

    const [updateProfile, { isLoading }] = useUpdateProfileMutation();
    const dispatch = useDispatch();

    useEffect(() => {
        if (userInfo) {
            setFirstName(userInfo.firstName);
            setLastName(userInfo.lastName);
            setEmail(userInfo.email);
        }
    }, [userInfo]);

    const submitHandler = async (e) => {
        e.preventDefault();

        try {
            const res = await updateProfile({ firstName, lastName, email }).unwrap();
            console.log(res);
            setFirstName(res.firstName);
            setLastName(res.lastName);
            setEmail(res.email);
            dispatch(setUserInfo({ ...res }));
            toast.success('Profile updated successfully', { position: "bottom-right", });
        } catch (error) {
            toast.error(error.data?.errorMessage || error.error, { position: "bottom-right", });
        }
    }

    return (<>
        <Row>
            <Col md={3}>
                <Nav defaultActiveKey="/account" className="flex-column">
                    <LinkContainer to='/account'>
                        <Nav.Link href='/login'> My Account</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to='/reviews'>
                        <Nav.Link >Reviews</Nav.Link>
                    </LinkContainer>
                    <LinkContainer to='/orders'>
                        <Nav.Link >Orders</Nav.Link>
                    </LinkContainer>
                </Nav>
            </Col>
            <Col md={9}>
                {isLoading ? <Loader /> :
                    <FormContainer isCenter={false}>
                        <h3>My Account</h3>
                        <Form onSubmit={submitHandler}>
                            <Form.Group controlId='firstName' className='py-3'>
                                <Form.Label>First Name</Form.Label>
                                <Form.Control
                                    type='text'
                                    placeholder='Enter First Name'
                                    value={firstName}
                                    onChange={(e) => setFirstName(e.target.value)}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group controlId='lastName' className='py-3'>
                                <Form.Label>Last Name</Form.Label>
                                <Form.Control
                                    type='text'
                                    placeholder='Enter Last Name'
                                    value={lastName}
                                    onChange={(e) => setLastName(e.target.value)}>
                                </Form.Control>
                            </Form.Group>
                            <Form.Group controlId='email' className='py-3'>
                                <Form.Label>Email Address</Form.Label>
                                <Form.Control
                                    type='email'
                                    placeholder='Enter Email'
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}>
                                </Form.Control>
                            </Form.Group>
                            <Button type='submit' variant='primary' disabled={isLoading}>
                                Update Profile
                            </Button>
                        </Form>
                    </FormContainer>}

            </Col>
        </Row>
    </>
    )
}

export default AccountScreen