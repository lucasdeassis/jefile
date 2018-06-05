import React, { Component } from 'react';
import Styled from 'styled-components';

const Form = Styled.form`
  width: 100%;
  height: 100%;
  text-align: center;
`;

const H2 = Styled.h2`
  font-size: 3em;
  padding-top: 3em;
`;

const Span = Styled.span`
  display: block;
`;

class JefileContainer extends Component {
  constructor() {
    super();
    this.state = {};
  }

  render() {
    return (
      <Form id="jefile-upload-container">
        <H2>
          <Span>File upload</Span>
          <Span>for you.</Span>
        </H2>
      </Form>
    );
  }
}

export default JefileContainer;
