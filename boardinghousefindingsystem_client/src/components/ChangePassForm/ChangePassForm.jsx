import React, { useEffect, useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import './ChangePassForm.css'

const ChangePassForm = () => {
    const inputRef = useRef(null);

    const [oldPassword, setOldPassword] = useState('')
    const [rePassword, setRePassword] = useState('')
    const [newPassword, setNewPassword] = useState('')


    useEffect(() => {
        if (inputRef.current) {
          inputRef.current.focus();
        }
    }, []);

    const handleSaveChange = () => {
        // xử lý gọi api

        // thông báo xử lý thay đổi mật khẩu thành công
        alert('Thay đổi mật khẩu thành công!')

        // xóa nội dung input về rỗng
        setOldPassword(prev => '')
        setNewPassword(prev => '')
        setRePassword(prev => '')
    }

    const handleCancelChange = () => {
        // xóa nội dung input về rỗng
        setOldPassword(prev => '')
        setNewPassword(prev => '')
        setRePassword(prev => '')
    }

    return ( 
        <>
            <div className='change-pass-form-wrapper'>
                <h1 className='subject'>Change your password</h1>
                <div className='container'>
                    <Link to='/' style={{ color: '#000' }}>Về trang chủ</Link>
                    <form className='form-change-password'>
                        <label className='title-input' for="password">Mật khẩu hiện tại:</label>
                        <input ref={inputRef} className='input-change-password' type="password" id="oldPassword" name="onlPassword"/>
                        <br />
                        <label className='title-input' for="password">Mật khẩu mới:</label>
                        <input className='input-change-password' type="password" id="newPassword" name="newPassword"/>
                        <br />
                        <label className='title-input' for="password">Nhập lại mật khẩu mới:</label>
                        <input className='input-change-password' type="password" id="rePassword" name="rePassword"/>
                        <br />
                        <input onClick={handleSaveChange} className='btn-save-change' type="submit" value="Lưu thay đổi"/>
                        <input onClick={handleCancelChange} className='btn-cancel-change' type="submit" value="Hủy bỏ"/>
                    </form>
                </div>
            </div>
        </>
    );
}
 
export default ChangePassForm;