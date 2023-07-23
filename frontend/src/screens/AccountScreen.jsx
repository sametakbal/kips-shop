import React from 'react'
import { Col, Row, Nav } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap'

const AccountScreen = () => {
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
                <h3>My Account</h3>
            </Col>
        </Row>
    </>
    )
}

export default AccountScreen