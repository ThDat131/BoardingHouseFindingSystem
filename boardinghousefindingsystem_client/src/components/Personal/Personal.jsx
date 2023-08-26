import "./personal.css";
import React from "react";

const Personal = () => {
  return (
    <>
      <div className="intro-wrapper d-flex">
        <div className="intro-wrapper__avatar d-flex">
          <img src="" alt="avatar user" className="avaImg" />
        </div>
        <h5 className="intro-wrapper__name">Nguyễn Bảo A</h5>
        <div className="intro-wrapper__contact">
          <span className="intro-wrapper__contact-phone">0842***123</span>
          <button className="btn-follow non-active">Follow</button>
        </div>
      </div>
      <div className="activities-container d-flex">
        <div className="activities-container__post-activity col60">
          <h6 className="activities-title">Các hoạt động gần đây</h6>
          <ul className="activity-list">
            {/* render các thẻ li vào đây */}
          </ul>
        </div>
        <div className="activities-container__information-detail col40">
          <h6 className="info-title">Thông tin cá nhân</h6>
          <ul className="info-list">
            <li className="info-list__member">
              <span className="tag">Name:</span>
              <span className="name-value" contenteditable="">Nguyễn Bảo A</span>
            </li>
            <li className="info-list__member">
              <span className="tag">Phone number:</span>
              <span className="phone-value" contenteditable="">0842***123</span>
            </li>
            <li className="info-list__member">
              <span className="tag">Gender:</span>
              <span className="dender-value" contenteditable="">Male</span>
            </li>
            <li className="info-list__member">
              <span className="dob">Name:</span>
              <span className="dob-value" contenteditable="">12/01/2001</span>
            </li>
            <li className="info-list__member">
              <span className="email">Email:</span>
              <span className="email-value" contenteditable="">a.nb120101@gmail.com</span>
            </li>
            <li className="info-list__member">
              <span className="address">Address:</span>
              <span className="address-value" contenteditable="">
                312/12/4A Nguyễn Kiệm, Gò Vấp
              </span>
            </li>
          </ul>
          <div className="btn-form">
            <button className="btn-saveChangeInfo">Save change</button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Personal;
