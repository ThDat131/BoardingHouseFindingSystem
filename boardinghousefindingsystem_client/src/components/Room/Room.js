import { useState } from 'react'
import './Room.css'
import { deleteRoom } from '../../services/ApiServices'
import AddRoomModal from '../AddRoomModal/AddRoomModal'
import { Link } from 'react-router-dom'
import { VNDCurrencyFormat } from '../../services/Utils'

const Room = ({ roomData, onDelete }) => {

    const [isOpenAddRoomModal, setIsAddRoomModal] = useState(false)
    
    const openAddRoomModal = () => {
        setIsAddRoomModal(true)
    }

    const closeAddRoomModal = () => {
        setIsAddRoomModal(false)
    }

    const deleteAction = () => {
        if (window.confirm("Bạn có chắc chắn muốn xoá") == true) {
            const deleteItemId = roomData.id
            deleteRoom(deleteItemId)
                .then(res => {
                    onDelete(deleteItemId)
                })
        }
        
    }

    const updateAction = () => {

    }

    return <>
        <div className="room rounded bg-warning p-3 mb-3">
            <div className="header">
                <h3>{roomData.address}</h3>
            </div>
            <div className="body">
                <div className="price">
                    <p>Giá: <span>{VNDCurrencyFormat.format(roomData.price)} - {roomData.acreage} m<sup>2</sup></span></p>
                </div>
                <div className="address ">
                    <p>Địa chỉ: {roomData.provinceId.fullName}, {roomData.districtId.fullName}, {roomData.wardId.fullName}</p>
                </div>
            </div>
            <div className="d-flex justify-content-between align-items-center mt-5 px-3">
                <Link to={`room/${roomData.id}`}>
                    <button className="btn btn-info" onClick={updateAction} >
                        Chỉnh sửa
                    </button>
                </Link>
                <button className='btn btn-danger' onClick={deleteAction}>Xoá</button>
            </div>

        </div>
    </>
}
export default Room