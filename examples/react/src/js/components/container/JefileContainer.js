import React, { Component } from 'react';
import Styled from 'styled-components';
import Dropzone from 'react-dropzone';

import { EXTENSIONS, MAX_SIZE } from '../../constants/file.json';
import UploadButton from '../presentational/UploadButton';

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

const WideDropzone = Styled(Dropzone)`
  position: 'absolute';
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  padding: '2.5em 0';
  background: 'rgba(0,0,0,.5)';
  text-align: 'center';
  color: '#fff';
`;

class JefileContainer extends Component {
  constructor() {
    super();
    this.state = {};
  }


  render() {
    let dropzoneRef;

    return (
      <Form id="jefile-upload-container">
        <WideDropzone
          ref={(node) => { dropzoneRef = node; }}
          accept={EXTENSIONS}
          maxSize={MAX_SIZE}
          multiple={false}
        >
          <H2>
            <Span>File upload</Span>
            <Span>for you.</Span>
          </H2>
        </WideDropzone>
        <UploadButton onClick={() => { dropzoneRef.open(); }}>
          {'Choose File or drag to screen'}
        </UploadButton>
      </Form>
    );
  }
}

export default JefileContainer;
