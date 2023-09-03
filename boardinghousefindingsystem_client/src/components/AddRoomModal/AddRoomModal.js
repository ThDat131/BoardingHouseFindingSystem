import { useEffect, useRef, useState } from "react"
import { addRoom, loadAllDistrictsByProvinceCode, loadAllProvinces, loadAllWardssByDistrictCode } from "../../services/ApiServices"
import { toast } from "react-toastify"

const AddRoomModal = ({ isOpen, onClose, onAdd }) => {

    const [provinces, setProvinces] = useState([])
    const [selectedProvinceCode, setselectedProvinceCode] = useState('')
    const [districts, setDistricts] = useState([])
    const [selectedDistrictCode, setSelectedDistrictCode] = useState('')
    const [wards, setWards] = useState([])
    const [selectedWardCode, setSelectedWardCode] = useState('')
    const [selectedStreet, setSelectedStreet] = useState('')
    const [selectedPrice, setselectedPrice] = useState('')
    const [selectedAcreage, setSelectedAcreage] = useState('')
    const proccessNotify = useRef(null)

    const [room, setRoom] = useState({
        "address": "",
        "price": "",
        "provinceId": "",
        "districtId": "",
        "wardId": "",
        "acreage": "",
    })
    const [disable, setDisable] = useState(false)

    useEffect(() => {
        loadAllProvinces()
            .then(provinces => {
                setProvinces(provinces.data)
                setDistricts([])
            })
            .catch(ex => {
                console.log(ex)
            })
    }, [])

    useEffect(() => {
        if (selectedProvinceCode !== '') {
            loadAllDistrictsByProvinceCode(selectedProvinceCode)
                .then(districts => {
                    setDistricts(districts.data)
                })
        }
    }, [selectedProvinceCode])

    useEffect(() => {
        if (selectedDistrictCode !== '') {
            loadAllWardssByDistrictCode(selectedDistrictCode)
                .then(wards => {
                    setWards(wards.data)
                })
        }
    }, [selectedDistrictCode])

    const handleProvinceChange = async (event) => {
        setselectedProvinceCode(event.target.value)
        setDistricts([])
        setWards([])
        setRoom((prevValues) => ({
            ...prevValues,
            "provinceId": event.target.value
        }))
    }

    const handleDistrictChange = (event) => {
        setSelectedDistrictCode(event.target.value)
        setWards([])
        setRoom((prevValues) => ({
            ...prevValues,
            "districtId": event.target.value
        }))
    }

    const handleWardChange = (event) => {
        setSelectedWardCode(event.target.value)
        setRoom((prevValues) => ({
            ...prevValues,
            "wardId": event.target.value
        }))
    }

    const handleStreetChange = (event) => {
        setSelectedStreet(event.target.value)
        setRoom((prevValues) => ({
            ...prevValues,
            "address": event.target.value
        }))
    }

    const handlePriceChange = (event) => {
        setselectedPrice(event.target.value)
        setRoom((prevValues) => ({
            ...prevValues,
            "price": event.target.value
        }))
        
    }

    const handleAcreageChange = (event) => {
        setSelectedAcreage(event.target.value)
        setRoom((prevValues) => ({
            ...prevValues,
            "acreage": event.target.value
        }))
    }

    const handleSubmit = () => {
        proccessNotify.current = toast.loading("Quá trình thêm phòng đang được xử lý...")
        setDisable(true)
        addRoom(room)
        .then(res => {
            if (res.status === 201) {
                onAdd(res.data)
                toast.update(proccessNotify.current, { 
                    render: "Thêm phòng thành công", 
                    type: "success", 
                    isLoading: false, 
                    autoClose: 5000, 
                    closeOnClick: true 
                })
                setRoom({
                    "address": "",
                    "price": "",
                    "provinceId": "",
                    "districtId": "",
                    "wardId": "",
                    "acreage": "",
                })

                defaultValue()

                onClose()
                setDisable(false)
            }
            else if (res.response.status === 400) {
                const errors = res.response.data
                const errorsRes = []
                for (let key in errors) {
                    if (errors.hasOwnProperty(key))
                        errorsRes.push(...errors[key])
                }
                toast.update(proccessNotify.current, { 
                    render: "Thêm phòng thất bại", 
                    type: "error", 
                    isLoading: false, 
                    autoClose: 5000, 
                    closeOnClick: true 
                })
                errorsRes.forEach((error) => {
                    toast.error(error)
                })

                // defaultValue()
            }
            
        })
    }

    const defaultValue = () => {
        setselectedProvinceCode("")
        setSelectedDistrictCode("")
        setSelectedWardCode("")
        setSelectedStreet("")
        setselectedPrice("")
        setSelectedAcreage("")
    }

    return <>
        <div className={`modal fade show ${isOpen ? 'showUp' : ''}`} tabIndex="-1" >
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Thêm nhà trọ</h5>
                        <button type="button" className="btn-close" onClick={onClose}></button>
                    </div>
                    <div className="modal-body">
                        <div className="address">
                            <h5 className='mt-2'>Thành phố/Tỉnh</h5>

                            <select className="form-select" onChange={handleProvinceChange}>
                                <option value="">-- Chọn một tùy chọn --</option>
                                {
                                    provinces.map((province, index) => {
                                        return <option key={index} value={province.code}>{province.name}</option>
                                    })
                                }

                            </select>
                            <h5 className='mt-2'>Quận/Huyện</h5>
                            <select className="form-select" onChange={handleDistrictChange}>
                                <option value="">-- Chọn một tùy chọn --</option>
                                {
                                    districts.map((district, index) => {
                                        return <option key={index} value={district.code}>{district.name}</option>
                                    })
                                }
                            </select>
                            <h5 className='mt-2'>Phường/Xã</h5>
                            <select className="form-select" onChange={handleWardChange}>
                                <option value="">-- Chọn một tùy chọn --</option>
                                {
                                    wards.map((ward, index) => {
                                        return <option key={index} value={ward.code}>{ward.name}</option>
                                    })
                                }
                            </select>
                            <div className="form-floating my-3">
                                <input type="text" className="form-control" id="house-number" placeholder="Số nhà" onChange={handleStreetChange} value={selectedStreet} />
                                <label htmlFor="house-number">Số nhà</label>
                            </div>
                        </div>
                        <div className="form-floating my-3">
                            <input type="number" className="form-control" id="house-price" placeholder="Giá tiền" onChange={handlePriceChange} value={selectedPrice}/>
                            <label htmlFor="house-price">Giá tiền</label>
                        </div>
                        <div className="form-floating my-3">
                            <input type="number" className="form-control" id="house-acreage" placeholder="Diện tích" onChange={handleAcreageChange} value={selectedAcreage}/>
                            <label htmlFor="house-acreage">Diện tích</label>
                        </div>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Đóng</button>
                        <button type="button" className="btn btn-primary" onClick={handleSubmit} disabled={disable}>Thêm</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}

export default AddRoomModal