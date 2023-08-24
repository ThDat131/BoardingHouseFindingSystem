import "../css/main.css";
import "../css/sign-up-host.css";
import React from "react";

const SignUpHost = () => {
    return (
        <>
            <div className="city-district d-flex">
                <input type="text" name="city" id="city" placeholder="Thành phố" className="col50"/>
                <input type="text" name="district" id="district" placeholder="Quận" className="col40"/>
            </div>
            <input type="text" name="address" id="address" placeholder="Địa chỉ" className=""/>
            <span className="title-images">Import more than 3 picture of accommodation</span>
            <div className="images-component">
                <button className="images-component__item">+</button>
                <button className="images-component__item">+</button>
                <button className="images-component__item">+</button>
                <button className="images-component__item">+</button>
                <button className="images-component__item">+</button>
            </div>
        </>
    );
}

export default SignUpHost;