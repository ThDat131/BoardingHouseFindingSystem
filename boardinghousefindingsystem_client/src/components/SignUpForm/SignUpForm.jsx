import "../css/main.css";
import "../css/sign-in.css";
import "../css/sign-up.css";
import React from "react";
import { useState } from "react";
import SignUpHost from "./SignUpHost";

const SignUpForm = () => {
  const customer = "customer";
  const host = "host";
  const [selectedRole, setSelectedRole] = useState("customer");

  const handleRoleChange = (event) => {
    setSelectedRole(event.target.value);
  };
  return (
    <>
      <div className="form-container signup">
        <h6 className="link-to__sign-in">Sign in</h6>
        <h2>Sign up</h2>
        <form action="" id="signup-form" method="post" className="d-flex">
          <input className="name" type="text" placeholder="Name" />
          <input className="phone" type="tel" placeholder="Phone number" />
          <input className="email" type="email" placeholder="Email" />
          <input className="password" type="password" placeholder="Password" />
          <input
            className="confirm-password"
            type="password"
            placeholder="Confirm password"
          />
          <div className="support-wrapper d-flex">
            <h6 className="ava-link">Choose avatar image</h6>
            <input type="checkbox" name="avaCheck" id="avaImg" />
          </div>
          <div className="role-wrapper d-flex">
            <input
              type="radio"
              name="customer"
              id="customer-id"
              checked={selectedRole === "customer"}
              onChange={handleRoleChange}
              value={customer}
            />
            <label htmlFor="customer-id">Customer account</label>
            <input
              type="radio"
              name="host"
              id="host-id"
              checked={selectedRole === "host"}
              onChange={handleRoleChange}
              value={host}
            />
            <label htmlFor="host-id">Host account</label>
          </div>
          <div className="host-info-container">
            {selectedRole === 'host' && <SignUpHost />}
          </div>
          <button type="submit" className="btn-signup">
            Sign up
          </button>
        </form>
        <div className="line-options d-flex">
          <hr className="line" />
          <span className="col30">or sign up with</span>
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

export default SignUpForm;
