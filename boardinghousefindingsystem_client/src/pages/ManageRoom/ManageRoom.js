import { useEffect, useState } from "react"
import Room from "../../components/Room/Room"
import { loadAllRoom } from "../../services/ApiServices"
import AddRoomModal from "../../components/AddRoomModal/AddRoomModal"
import Loading from "../../components/Loading/Loading"

const ManageRoom = () => {

    const [isOpenAddRoomModal, setIsAddRoomModal] = useState(false)
    const [isLoading, setIsLoading] = useState(true)

    const openAddRoomModal = () => {
        setIsAddRoomModal(true)
    }

    const closeAddRoomModal = () => {
        setIsAddRoomModal(false)
    }

    const [rooms, setRooms] = useState([])
    useEffect(() => {
        loadAllRoom()
        .then(res => {
            setRooms(res.data)
            setIsLoading(false)
        })
        .catch(ex => {
            console.log(ex)
        })
    }, [])

    const addRoom = (room) => {
        setRooms([...rooms, room])
    }

    const handleDeleteRoom = (roomId) => {
        const updatedRooms = rooms.filter(room => room.id !== roomId)
        setRooms(updatedRooms)
    }
    

    return <>
        <AddRoomModal isOpen={isOpenAddRoomModal} onClose={closeAddRoomModal} onAdd={addRoom} />
        <div className="container">
            <h1>Quản lý nhà trọ</h1>
            <button className="btn btn-info mb-3" onClick={openAddRoomModal}>Thêm nhà trọ</button>
            <div className="row">
                {
                    isLoading ? <Loading /> :
                    rooms.map((room, index) => {
                        return <div key={index} className="col-3 d-flex align-items-center justify-content-center gap-2 flex-fill">
                                <Room roomData={room} onDelete={handleDeleteRoom} />    
                            </div>
                    })
                }
            </div>
            
        </div>
    </>
    
}

export default ManageRoom