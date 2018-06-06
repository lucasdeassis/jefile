import React from 'react';
import PropTypes from 'prop-types';
import Styled from 'styled-components';

const Button = Styled.span`
  color: #6E6E83;
  font-size: 1.125em;
  line-height: 1.3;
  transition: opacity 250ms;
  cursor: pointer;
`;

const SpanLabel = Styled.span`
  display: inline-block;
  line-height: 1.4;
  vertical-align: top;
  white-space: normal;
`;

const SpanUnderline = Styled.span`
  position: absolute;
  display: block;
  bottom: 0;
  left: 0;
  right: 0;
  height: 4px;
  width: 100%;
  line-height: 4px;
  overflow: hidden;
`;

const SpanImg = Styled.span`
  height: 100%;
  width: 100%;
  max-width: none;
  display: block;
  position: absolute;
  left: 0;
  top: 0;
  background-image: url("http://www.mad.ac/content/themes/mad/imgs/underline-2.png");
  background-size: 100% 100%;
`;

const UploadButton = ({ children, onClick }) => (
  <Button onClick={onClick}>
    <SpanLabel>
      <span>
        {children}
      </span>
      <SpanUnderline>
        <span>
          <SpanImg />
        </span>
      </SpanUnderline>
    </SpanLabel>
  </Button>
);

UploadButton.propTypes = {
  onClick: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
};

export default UploadButton;
