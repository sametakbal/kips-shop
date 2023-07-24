import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { toast } from 'react-toastify';
import { setUserInfo } from '../slices/authSlice';
import { useUpdateProfileMutation } from '../slices/usersApiSlice';
import { Button, Form } from 'react-bootstrap';
import Loader from './Loader';
import FormContainer from './FormContainer';

const UpdateProfile = () => {
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
            setFirstName(res.firstName);
            setLastName(res.lastName);
            setEmail(res.email);
            dispatch(setUserInfo({ ...res }));
            toast.success('Profile updated successfully', { position: "bottom-right", });
        } catch (error) {
            toast.error(error.data?.errorMessage || error.error, { position: "bottom-right", });
        }
    }

    return (
        <>
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

        </>
    )
}

export default UpdateProfile