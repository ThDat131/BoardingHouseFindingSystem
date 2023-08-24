import "../css/main.css";
import React, { useState } from "react";
import SignInForm from "./SignInForm";
import SignUpForm from "./SignUpForm";
import Personal from "./Personal";

const Base = () => {
  const [current, setState] = useState('signin');

  const handleSignInClick = () => {
    setState('signin');
  };

  const handleSignUpClick = () => {
    setState('signup');
  };

  const handlePersonalClick = () => {
    setState('personal');
  };

  const renderActiveComponent = () => {
    switch (current) {
      case 'signup':
        return <SignUpForm />;
      case 'signin':
        return <SignInForm />;
      default:
        return <Personal />;
    }
  };

  return (
    <>
      <button onClick={handleSignInClick}>Sign In</button>
      <button onClick={handleSignUpClick}>Sign Up</button>
      <button onClick={handlePersonalClick}>Personal</button>

      {renderActiveComponent(current)}
    </>
  );
};

export default Base;
