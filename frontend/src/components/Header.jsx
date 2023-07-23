import React from 'react'
import { Badge, Container, Nav, NavDropdown, Navbar } from 'react-bootstrap'
import { FaShoppingCart, FaUser } from 'react-icons/fa'
import { MdFavorite } from 'react-icons/md'
import { LinkContainer } from 'react-router-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import logo from '../assets/logo.png'
import { logout } from '../slices/authSlice'
import { toast } from 'react-toastify'

const Header = () => {
    const { cartItems } = useSelector(state => state.cart);
    const { userInfo } = useSelector(state => state.auth);
    const dispatch = useDispatch();

    const logOutHandler = () => {
        dispatch(logout());
        toast.success('Logout successful');
    }


    return (
        <header>
            <Navbar bg='dark' variant='dark' expand='md' collapseOnSelect>
                <Container>
                    <LinkContainer to='/'>
                        <Navbar.Brand ><img src={logo} alt='logo' height={50} /></Navbar.Brand>
                    </LinkContainer>
                    <Navbar.Toggle aria-controls='basic-navbar-nav' />
                    <Navbar.Collapse id='basic-navbar-nav'>
                        <Nav className='ms-auto'>
                            <LinkContainer to='/cart'>
                                <Nav.Link ><FaShoppingCart /> Cart
                                    {cartItems.length > 0 && (
                                        <Badge pill bg='success' style={{ marginLeft: '5px' }}>{cartItems.reduce((a, c) => a + c.qty, 0)}</Badge>
                                    )}
                                </Nav.Link>
                            </LinkContainer>
                            <LinkContainer to='/favorites'>
                                <Nav.Link ><MdFavorite /> My Favorites
                                </Nav.Link>
                            </LinkContainer>
                            {userInfo ? (
                                <NavDropdown title={userInfo.firstName} id='username'>
                                    <LinkContainer to='/account'>
                                        <NavDropdown.Item>Profile</NavDropdown.Item>
                                    </LinkContainer>
                                    <NavDropdown.Item onClick={logOutHandler}>Logout</NavDropdown.Item>
                                </NavDropdown>
                            ) : (
                                <LinkContainer to='/login'>
                                    <Nav.Link href='/login'><FaUser /> Sign In</Nav.Link>
                                </LinkContainer>
                            )}

                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    )
}

export default Header