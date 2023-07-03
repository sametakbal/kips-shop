import React, { useEffect, useState } from 'react'
import FormContainer from '../components/FormContainer';
import { Button, Col, Form, Row } from 'react-bootstrap';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useRegisterMutation } from '../slices/usersApiSlice';
import { setCredentials } from '../slices/authSlice';
import Loader from '../components/Loader';
import { toast } from "react-toastify";

const RegisterScreen = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [register, { isLoading }] = useRegisterMutation();

    const { userInfo } = useSelector(state => state.auth);

    const { search } = useLocation();
    const sp = new URLSearchParams(search);
    const redirect = sp.get('redirect') || '/';

    useEffect(() => {
        if (userInfo) {
            navigate(redirect);
        }
    }, [redirect, navigate, userInfo]);

    const submitHandler = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            toast.error('Passwords do not match');
        } else {
            try {
                const res = await register({ firstName, lastName, email, password }).unwrap();
                dispatch(setCredentials({ ...res }));
                toast.success('Login successful');
                navigate(redirect);
            } catch (error) {
                toast.error(error.data?.errorMessage || error.error);
            }
        }

    }

    return (
        <FormContainer>
            <h1>Sign Up</h1>
            <Form onSubmit={submitHandler}>
                <Form.Group controlId='email' className='py-3'>
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
                <Form.Group controlId='password' className='py-3'>
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type='password'
                        placeholder='Enter Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}>
                    </Form.Control>
                </Form.Group>
                <Form.Group controlId='confirmPassword' className='py-3'>
                    <Form.Label>Password Confirm</Form.Label>
                    <Form.Control
                        type='password'
                        placeholder='Enter Password Confirm'
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}>
                    </Form.Control>
                </Form.Group>
                <Button type='submit' variant='primary' disabled={isLoading}>
                    Register
                </Button>
                {isLoading && <Loader />}
            </Form>
            <Row className='py-3'>
                <Col>
                    Already have an account? <Link to='/login'>Login</Link>
                </Col>
            </Row>
        </FormContainer>
    )
}

export default RegisterScreen