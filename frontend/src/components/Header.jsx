import React from 'react'
import { Badge, Container, Nav, Navbar } from 'react-bootstrap'
import { FaShoppingCart, FaUser } from 'react-icons/fa'
import { MdFavorite } from 'react-icons/md'
import { LinkContainer } from 'react-router-bootstrap'
import { useSelector } from 'react-redux'
import logo from '../assets/logo.png'
const Header = () => {
    const { cartItems } = useSelector(state => state.cart);

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
                            <LinkContainer to='/login'>
                                <Nav.Link href='/login'><FaUser /> Sign In</Nav.Link>
                            </LinkContainer>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    )
}

export default Header