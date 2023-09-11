import "./sign-up.css";
import React, { useContext, useRef } from "react";
import { useState } from "react";
import SignUpHost from "../SignUpHost/SignUpHost";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { signUpLandLord, signUpTentant } from "../../services/ApiServices";
import Loading from "../Loading/Loading";
import { toast } from "react-toastify";
import { MyUserContext } from "../../App";

const SignUpForm = () => {
  const [user, dispatch] = useContext(MyUserContext)
  const [role, setRole] = useState("-1");
  const avatar = useRef()
  const [userRegister, setUserRegister] = useState({
    "username": "",
    "password": "",
    "confirmPassword": "",
    "active": "0",
    "role": "-1",
    "avatar": "",
    "fullName": "",
    "address": "",
    "phone": "",
    "personalId": "",
    "email": "",
    "images": ""
  })

  const [address, setAddress] = useState("")
  const [files, setFiles] = useState([])
  const [isLoading, setIsLoading] = useState(false)
  const navigate = useNavigate();


  const changeInfoHandler = (evt, field) => {
    setUserRegister(prevValue => {
      return {
        ...prevValue,
        [field]: evt.target.value
      }
    })
  }

  const handleAddressData = (data) => {
    if (role !== "-1") {
      setUserRegister(prevValue => {
        return {
          ...prevValue,
          "address": data
        }
      })
      return data
    }

  }

  const handleFilesData = (data) => {
    if (role !== "-1") {
      setFiles(data)
      return data
    }
  }

  const handleSignup = (evt) => {
    evt.preventDefault()
    console.log(userRegister)
    setIsLoading(true)

    if (userRegister.password !== userRegister.confirmPassword) {
      toast.error("Vui lòng xác nhận mật khẩu chính xác")
      setIsLoading(false)
      return;
    }

    const formData = new FormData()

    for (let field in userRegister) {
      if (field !== "confirmPassword") {
        formData.append(field, userRegister[field])
        formData.delete("avatar");
        formData.delete("images");
      }
    }

    if (avatar.current.files[0])
      formData.append("avatar", avatar.current.files[0])
    else {
      toast.error("Vui lòng thêm avatar");
      setIsLoading(false)
      return;
    }

    if (userRegister.role === "0") {
      if (!files.current || files.current.length < 3) {
        toast.error("Vui lòng thêm ít nhất 3 ảnh về nhà trọ");
        setIsLoading(false)
        return;
      }
      for (let i = 0; i < files.current.length; i++)
        formData.append("images", files.current[i])

      signUpLandLord(formData)
        .then((res) => {
          if (res.status === 201) {
            setIsLoading(false)
            toast.success("Đăng ký thành công")
            navigate("/dang-nhap")
          }
          else if (res.response.status === 400) {
            const errors = res.response.data
            const errorsRes = []
            for (let key in errors) {
              if (errors.hasOwnProperty(key))
                errorsRes.push(...errors[key])
            }

            errorsRes.map(error => {
              return toast.error(error)
            })
            setIsLoading(false)
          }
        })
        .catch(err => {
          console.error(err)
        })
    }
    else if (userRegister.role === "-1") {
      signUpTentant(formData)
        .then((res) => {
          if (res.status === 201) {
            setIsLoading(false)
            toast.success("Đăng ký thành công")
            navigate("/dang-nhap")
          }
          else if (res.response.status === 400) {
            const errors = res.response.data
            const errorsRes = []
            for (let key in errors) {
              if (errors.hasOwnProperty(key))
                errorsRes.push(...errors[key])
            }

            errorsRes.map(error => {
              return toast.error(error)
            })
            setIsLoading(false)
            
          }
        })
        .catch(err => {
          console.error(err)
        })
    }


    // for (var pair of formData.entries()) {
    //   console.log(pair[0] + ', ' + pair[1]);
    // }
  }

  const initDefaultValue = () => {
    setUserRegister({
      "username": "",
      "password": "",
      "confirmPassword": "",
      "active": "0",
      "role": "-1",
      "avatar": "",
      "fullName": "",
      "address": "",
      "phone": "",
      "personalId": "",
      "email": "",
      "images": ""
    })
    setRole("-1")
  }
  if (user !== null){
    return <Navigate to="/" />
    
  }
  return (
    <>
      <div className="form-container signup">
        <Link to="/dang-nhap" style={{ color: "#fff" }} className="link-to__sign-in">Đăng nhập</Link>
        <h2>Đăng ký</h2>
        <form action="" id="signup-form" method="post" className="d-flex" onSubmit={handleSignup}>
          <input className="name p-2" type="text" placeholder="Tên đăng nhập" value={userRegister.username} onChange={e => changeInfoHandler(e, "username")} />
          <input className="name p-2" type="text" placeholder="Họ tên" value={userRegister.fullName} onChange={e => changeInfoHandler(e, "fullName")} />
          <input className="name p-2" type="text" placeholder="Chứng minh nhân dân" value={userRegister.personalId} onChange={e => changeInfoHandler(e, "personalId")} />
          <input className="phone p-2" type="tel" placeholder="Số điện thoại" value={userRegister.phone} onChange={e => changeInfoHandler(e, "phone")} />
          <input className="email p-2" type="email" placeholder="Email" value={userRegister.email} onChange={e => changeInfoHandler(e, "email")} />
          <input className="password p-2" type="password" placeholder="Mật khẩu" value={userRegister.password} onChange={e => changeInfoHandler(e, "password")} />
          <input
            className="confirm-password p-2"
            type="password"
            placeholder="Xác nhận mật khẩu"
            onChange={e => changeInfoHandler(e, "confirmPassword")}
            value={userRegister.confirmPassword}
          />
          <input className="p-2" type="file" ref={avatar} />
          <div className="role-wrapper d-flex">
            <input
              type="radio"
              name="role"
              id="customer-id"
              onChange={e => {
                changeInfoHandler(e, "role")
                setRole("-1")
                setUserRegister(prevValue => {
                  return {
                    ...prevValue,
                    "address": "",
                  }
                })
                setFiles([])
              }}
              checked={role === "-1"}
              value="-1"
            />
            <label htmlFor="customer-id">Customer account</label>
            <input
              type="radio"
              name="role"
              id="host-id"
              onChange={e => {
                changeInfoHandler(e, "role")
                setRole("0")
                setUserRegister(prevValue => {
                  return {
                    ...prevValue,
                    "address": "",
                  }
                })
                setFiles([])
              }}
              checked={role === "0"}
              value="0"
            />
            <label htmlFor="host-id">Host account</label>
          </div>
          <div className="host-info-container">
            {role === "0" && <SignUpHost addressData={handleAddressData} filesData={handleFilesData} />}
          </div>
          <button type="submit" className="btn-signup" disabled={isLoading}>
            <div className="d-flex gap-2 justify-content-center align-items-center">
              <span>Đăng ký</span>
              {isLoading ? <Loading /> : ""}
            </div>
          </button>
        </form>
        {/* <div className="line-options d-flex">
          <hr className="line" />
          <span className="col30">or sign up with</span>
          <hr className="line" />
        </div>
        <div className="options-wrapper">
          <ul className="option-list">
            <li className="option-list__item">Google</li>
            <li className="option-list__item">Facebook</li>
          </ul>
        </div> */}
      </div>
    </>
  );
};

export default SignUpForm;
