import React from 'react'
import { Nav } from 'react-bootstrap'

const CheckoutSteps = ({ currentStep }) => {

    const steps = [
        { name: 'Sign In', link: '/login' },
        { name: 'Shipping', link: '/shipping' },
        { name: 'Payment', link: '/payment' },
        { name: 'Place Order', link: '/placeorder' },
    ];

    return (
        <Nav className='justify-content-center mb-4'>
            {steps.map((step, index) => (
                <Nav.Item key={index}>
                    {(currentStep >= (index + 1)) ? (
                        <Nav.Link to={step.link}>{step.name}</Nav.Link>
                    ) : (
                        <Nav.Link disabled>{step.name}</Nav.Link>
                    )}
                </Nav.Item>
            ))}
        </Nav>
    )
}

export default CheckoutSteps