import { React, useEffect, useRef, useState } from "react";
import { Link, useAsyncError } from "react-router-dom";
import "./Personal.css";
import { changeTentantDetails, getCurrentUser, getTentantDetails } from "../../services/ApiServices";
import Loading from "../Loading/Loading";

const Personal = () => {
  const avatar = useRef();
  const [data, setData] = useState({});
  const [selectedImage, setSelectedImage] = useState(null);
  const [fullName, setFullName] = useState("");
  const [phone, setPhone] = useState("")
  const [email, setEmail] = useState("")
  const [address, setAddress] = useState("")
  const [follow, setFollow] = useState(false);
  const [details, setDetails] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    getCurrentUser().then((user) => {
      setData(user.data);
      setSelectedImage(data.avatar);
      getTentantDetails().then((info) => {
        setDetails(info.data);
        return info.data;
      }).then((value) => {
        setFullName(value.fullName)
        setEmail(value.email)
        setPhone(value.phone)
        if (data.role === 0) {
          setAddress(value.address)
        }
        setIsLoading(false);
      });
    });
  }, []);

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
    const formData = new FormData();
    formData.append("name", fullName);
    formData.append("phone", phone);
    formData.append("email", email);
    formData.append("address", address);
    formData.append("avatar", avatar.current.file[0]);

    // xử lý gọi api
    changeTentantDetails(formData)

    // thông báo thay đổi thông tin cá nhân thành công
  };

  const handleFollow = () => {
    setFollow(!follow);
  };

  if (isLoading) {
    return <Loading />;
  }

  return (
    <>
      <div className="intro-wrapper d-flex">
        <div className="intro-wrapper__avatar d-flex">
          {selectedImage && (
            <img
              src={selectedImage}
              alt="avatar-user"
              className="avaImg"
              style={{ maxWidth: "100%" }}
            />
          )}
        </div>
        <h5 className="intro-wrapper__name">{fullName}</h5>
        <div className="intro-wrapper__contact">
          <span className="intro-wrapper__contact-phone">{phone}</span>
          <button onClick={handleFollow} className="btn-follow">
            {follow === true ? "Bỏ theo dõi" : "Theo dõi"}
          </button>
        </div>
        <Link to="/doi-mat-khau">
          <button className="btn-change-password">Đổi mật khẩu</button>
        </Link>
      </div>
      <div className="activities-container d-flex">
        <div className="activities-container__post-activity col60">
          <h6 className="activities-title">Các hoạt động gần đây</h6>
          <ul className="activity-list">{/* render các thẻ li vào đây */}</ul>
        </div>
        <div className="activities-container__information-detail col40">
          <h6 className="info-title">Thông tin cá nhân</h6>
          <ul className="info-list">
            <li className="info-list__member">
              <label for="changeAvatar">Đổi ảnh dại diện</label>
              <input
                id="changeAvatar"
                className="input-set-avatar"
                type="file"
                onChange={handleImageChange}
                ref={avatar}
              />
            </li>
            <li className="info-list__member">
              <span className="tag">Họ tên:</span>
              <span className="name-value" contenteditable="">
                {fullName}
              </span>
            </li>
            <li className="info-list__member">
              <span className="tag">Số điện thoại:</span>
              <span className="phone-value" contenteditable="">
                {phone}
              </span>
            </li>
            <li className="info-list__member">
              <span className="email">Email:</span>
              <span className="email-value" contenteditable="">
                {email}
              </span>
            </li>
            {data.role === 0 ? (
              <li className="info-list__member">
                <span className="address">Địa chỉ:</span>
                <span className="address-value" contenteditable="">
                  {address}
                </span>
              </li>
            ) : (
              ""
            )}
          </ul>
          <div className="btn-form">
            <button 
              onClick={handleSaveInfoDetail}
              className="btn-saveChangeInfo">
              Lưu thay đổi
              </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Personal;
