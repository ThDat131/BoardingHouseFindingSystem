import {React, useRef, useState} from "react";
import { Link } from "react-router-dom";
import "./Personal.css";

const Personal = () => {
  const avatar = useRef()
  const [selectedImage, setSelectedImage] = useState(null);
  const [follow, setFollow] = useState(false)

  const handleImageChange = (event) => {
    const file = event.target.files[0]; // Lấy file đầu tiên trong danh sách đã chọn

    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        setSelectedImage(e.target.result);
      };

      reader.readAsDataURL(file);
    }
  };

  const handleSaveInfoDetail = () => {
    // xử lý gọi api

    // thông báo thay đổi thông tin cá nhân thành công
  }

  const handleFollow = () => {
    setFollow(!follow)
  }

  return (
    <>
      <div className="intro-wrapper d-flex">
        <div className="intro-wrapper__avatar d-flex">
          {selectedImage && (
            <img src={selectedImage} alt="avatar-user" className="avaImg" style={{ maxWidth: '100%' }}/>
          )}
          
        </div>
        <h5 className="intro-wrapper__name">Nguyễn Bảo A</h5>
        <div className="intro-wrapper__contact">
          <span className="intro-wrapper__contact-phone">0842***123</span>
          <button onClick={handleFollow} className="btn-follow">
            {follow === true ? 'Bỏ theo dõi' : 'Theo dõi'}
          </button>
        </div>
        <Link to='/doi-mat-khau'>
          <button className="btn-change-password">Đổi mật khẩu</button>
        </Link>
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
              <label for='changeAvatar'>Đổi ảnh dại diện</label>
              <input id="changeAvatar" className="input-set-avatar" type="file" onChange={handleImageChange} ref={avatar}/>
            </li>
            <li className="info-list__member">
              <span className="tag">Họ tên:</span>
              <span className="name-value" contenteditable="">Nguyễn Bảo A</span>
            </li>
            <li className="info-list__member">
              <span className="tag">Số điện thoại:</span>
              <span className="phone-value" contenteditable="">0842***123</span>
            </li>
            <li className="info-list__member">
              <span className="tag">Giới tính:</span>
              <span className="dender-value" contenteditable="">Name</span>
            </li>
            <li className="info-list__member">
              <span className="dob">Ngày sinh:</span>
              <span className="dob-value" contenteditable="">12/01/2001</span>
            </li>
            <li className="info-list__member">
              <span className="email">Email:</span>
              <span className="email-value" contenteditable="">a.nb120101@gmail.com</span>
            </li>
            <li className="info-list__member">
              <span className="address">Địa chỉ:</span>
              <span className="address-value" contenteditable="">
                312/12/4A Nguyễn Kiệm, Gò Vấp
              </span>
            </li>
          </ul>
          <div className="btn-form">
            <button className="btn-saveChangeInfo">Lưu thay đổi</button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Personal;
