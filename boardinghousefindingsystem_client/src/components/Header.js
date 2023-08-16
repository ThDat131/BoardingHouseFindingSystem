const Header = () => {
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
                            <li className="mx-2">
                                <a className="p-2 text-dark" style={decor_none} href="">
                                    <i className="fa-solid fa-heart mx-1"></i>
                                    Yêu thích
                                </a>
                            </li>
                            <li className="mx-2">
                                <a className="p-2 text-dark" style={decor_none} href="">
                                    <i className="fa-solid fa-right-to-bracket mx-1"></i>
                                    Đăng ký
                                </a>
                            </li>
                            <li className="mx-2">
                                <a className="p-2 text-dark" style={decor_none} href="">
                                    <i className="fa-solid fa-user-plus mx-1"></i>
                                    Đăng nhập
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                
            </div>
        </nav>
        <nav className="navbar navbar-expand-sm bg-dark ">
            <div className="container">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <a className="nav-link text-light" href="#">Trang chủ</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link text-light" href="#">Tin thuê phòng trọ</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link text-light" href="#">Tin tìm phòng trọ</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link text-light" href="#">Quản lý phòng trọ</a>
                    </li>
                </ul>
            </div>

        </nav>
    </>
}
export default Header