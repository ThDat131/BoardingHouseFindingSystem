import { Link, useNavigate } from "react-router-dom"
import './Header.css'
import { useContext, useState } from "react";
import { MyUserContext } from "../../App";
import { toast } from "react-toastify";

const Header = () => {

    const [user, dispatch] = useContext(MyUserContext);
    const [openOption, setOpenOption] = useState(false)
    const navigate = useNavigate();

    const logout = () => {
        dispatch({
            "type": "logout"
        })
        navigate("/dang-nhap")
        toast.success("Đã đăng xuất!")
    }

    const handleOpenOption = () => {
        setOpenOption(!openOption)
    }   

    let decor_none = {
        textDecoration: "none"
    }
    return <>
        <nav className="navbar navbar-expand-sm bg-light">
            <div className="container">
                <div className="row w-100 justify-content-between">
                    <div style={{width: '120px'}} className="col-6">
                        <img className="w-100" src={`${process.env.PUBLIC_URL}/assets/logo/logo_black.png`} alt="logo" />
                    </div>
                    <div className="col-6 d-flex align-items-center justify-content-center">
                        <ul style={{marginBottom: 0}} className="d-flex align-items-center justify-content-center">
                            {
                                user === null ? 
                                <>
                                    <li className="mx-2">
                                        <Link to="/dang-ky" className="p-2 text-dark" style={decor_none} href="">
                                            <i className="fa-solid fa-user-plus mx-1"></i>
                                            Đăng ký
                                        </Link>
                                    </li>
                                    <li className="mx-2">
                                        <Link to="/dang-nhap" className="p-2 text-dark" style={decor_none} href="">
                                            <i className="fa-solid fa-user-plus mx-1"></i>
                                            Đăng nhập
                                        </Link>
                                    </li>
                                </> :
                                <>
            
                                        <div className="current-user d-flex justify-content-center align-items-center gap-2" onClick={handleOpenOption}>
                                        <img src={user.avatar} alt="" width={30} height={30} className="rounded-circle" />
                                        <span className="nav-link text-succes">{ user.role === 0 ? user.landLord.fullName : (user.role === -1 ? user.tentant.fullName : user.username)}</span>
                                            <div className={openOption ? 'current-user-option active' : 'current-user-option'}  >
                                            <ul>
                                                <li>
                                                    <Link style={{textDecoration: "none", color:"#000"}} to="/trang-ca-nhan">
                                                        Thông tin cá nhân
                                                    </Link>
                                                </li>

                                                {
                                                        user.role === 0 ? <Link style={{textDecoration: "none", color:"#000"}} to="/quan-ly-nha-tro"><li>Quản lý nhà trọ</li></Link> : ""
                                                }
                                                <li onClick={logout}>Đăng xuất</li>
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    

                                    {/* <button className="btn btn-secondary" onClick={logout}>Đăng xuất</button> */}
                 
                                </>

                            }
                            
                        </ul>
                    </div>
                </div>
                
            </div>
        </nav>
        <nav className="navbar navbar-expand-sm" style={{backgroundColor: "#000"}}>
            <div className="container">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link to={`/`} className="nav-link text-light" >
                            Trang chủ
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link text-light" to={`/tin-thue-nha-tro`}>Tin thuê phòng trọ</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link text-light" to={`/tin-tim-phong-tro`}>Tin tìm phòng trọ</Link>
                    </li>
                </ul>
            </div>

        </nav>
    </>
}
export default Header