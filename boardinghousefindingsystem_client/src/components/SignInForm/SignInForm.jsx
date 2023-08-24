import "../css/main.css";
import "../css/sign-in.css";
import React from "react";
import { Link } from "react-router-dom";

const SignInForm = () => {
  return (
    <>
      <div className="form-container signin">
        <h2>Sign in</h2>
        <form action="" id="signin-form" method="post" className="d-flex">
          <input className="email" type="email" placeholder="Email" />
          <input className="password" type="password" placeholder="Password" />
          <div className="support-wrapper d-flex">
            <h6 className="link-to__create-account">Create account</h6>
            <h6 className="link-to__forgot-pass">Forgot your password?</h6>
          </div>
          <button type="submit" className="btn-signin">
            Sign in
          </button>
        </form>
        <div className="line-options d-flex">
          <hr className="line" />
          <span className="col30">or sign in with</span>
          <hr className="line" />
        </div>
        <div className="options-wrapper">
          <ul className="option-list">
            <li className="option-list__item">Google</li>
            <li className="option-list__item">Facebook</li>
          </ul>
        </div>
      </div>
    </>
  );
};

export default SignInForm;
