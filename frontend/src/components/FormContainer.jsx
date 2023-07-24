import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'

const FormContainer = ({ children, isCenter = true }) => {
    return (
        <Container>
            <Row className={isCenter ? 'justify-content-md-center' : ''}>
                <Col xs={12} md={6}>
                    {children}
                </Col>
            </Row>
        </Container>
    )
}

export default FormContainer