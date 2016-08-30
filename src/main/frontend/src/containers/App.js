import React from 'react';

import { Header } from '../components/Header';
import { Footer } from '../components/Footer';

class App extends React.Component {
  render() {
    return (
      <div>
        <Header />
        <div>Spark React Redux Boilerplate</div>
        <Footer />
      </div>
    );
  }
}

export { App };
