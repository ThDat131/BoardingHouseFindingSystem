import "./sign-in.css";
import React, { useContext } from "react";
import { useState } from "react";
import { Link, Navigate } from "react-router-dom";
import { getCurrentUser, login } from "../../services/ApiServices";
import { ToastContainer, toast } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Loading from "../Loading/Loading"
import { MyUserContext } from "../../App";
import cookie from "react-cookies";

const SignInForm = () => {

  const [user, dispatch] = useContext(MyUserContext)

  const [username, setUsername] = useState("")
  const [password, setPasssword] = useState("")
  const [isLoading, setIsLoading] = useState(false)

  const loginHandle = (evt) => {
    evt.preventDefault()
    setIsLoading(true)
    login(username, password)
    .then((res) => {
      cookie.save("token", res.data)
      getCurrentUser()
      .then((res) => {
        if (res.data.active === false) {
          console.log(res)
          setIsLoading(false)
          toast.error("Tài khoản chưa được kích hoạt")
          return
        }
        let user = res.data
        cookie.save("user", user);
        console.log(res.data)
        toast.success("Đăng nhập thành công")
        dispatch({
          "type": "login",
          "payload": user
        })
      })
    })
    .catch(ex => {
      toast.error("Sai tài khoản hoặc mật khẩu")
      console.error(ex)
      setIsLoading(false)
    })
  }
  
  if (user !== null) 
    return <Navigate to="/" />

  return (
    <>

      <div className="form-container signin">
        <h2>Đăng nhập</h2>
        <form action="" id="signin-form" method="post" className="d-flex" onSubmit={loginHandle}>
          <input className="email p-2" value={username} onChange={e => setUsername(e.target.value)} type="text" placeholder="Tên đăng nhập" />
          <input className="password p-2" value={password} onChange={e => setPasssword(e.target.value)} type="password" placeholder="Mật khẩu" />
          <div className="support-wrapper d-flex">
            <Link to="/dang-ky" style={{color: '#fff'}}>
              <h6 className="link-to__create-account">Tạo tài khoản</h6>
            </Link>
            <h6 className="link-to__forgot-pass">Quên mật khẩu</h6>
          </div>
          <button type="submit" className="btn-signin p-3" disabled={isLoading}>
            <div className="d-flex gap-2 justify-content-center align-items-center">
              <span>Đăng nhập</span>
              {isLoading ? <Loading /> : ""}
            </div>
          </button>
        </form>
        {/* <div className="line-options d-flex">
          <hr className="line" />
          <span>hoặc đăng nhập với</span>
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

export default SignInForm;
