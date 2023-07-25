import React, {  useState } from 'react'
import { Col, Row, Nav } from 'react-bootstrap';
import UpdateProfile from '../components/UpdateProfile';



const AccountScreen = () => {

    const [activePage, setActivePage] = useState(1);

    return (<>
        <Row>
            <Col md={3}>
                <Nav className="flex-column">
                    <Nav.Link onClick={() => setActivePage(1)} > My Account</Nav.Link>
                    <Nav.Link onClick={() => setActivePage(2)} >Reviews</Nav.Link>
                    <Nav.Link onClick={() => setActivePage(3)} >Orders</Nav.Link>
                </Nav>
            </Col>
            <Col md={9}>
                {activePage === 1 && <UpdateProfile />}
            </Col>
        </Row>
    </>
    )
}

export default AccountScreen