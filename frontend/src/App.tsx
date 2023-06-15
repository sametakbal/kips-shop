import React from 'react'
import Header from './components/Header'
import { Container } from 'react-bootstrap'
import Footer from './components/Footer'

export const App = () => {
  return (
    <>
      <Header />
      <Container>
        <main className='py-3'>
          <h1>Welcome to Kips</h1>
        </main>
      </Container>
      <Footer />
    </>
  )
}

export default App