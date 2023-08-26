import "./SigninSignup.css";
import React, { useState } from "react";
import SignUpForm from "../../components/SignUpForm/SignUpForm";
import SignInForm from "../../components/SignInForm/SignInForm";
import Personal from "../../components/Personal/Personal";

const SigninSignup = () => {
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

export default SigninSignup;
