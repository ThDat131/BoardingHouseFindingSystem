import { useEffect, useState } from "react"
import { getRoomById, loadAllDistrictsByProvinceCode, loadAllProvinces, loadAllWardssByDistrictCode, updateRoom } from "../../services/ApiServices"
import { Link, useParams, Navigate, useNavigate } from "react-router-dom"
import Loading from "../../components/Loading/Loading"

const EditRoom = () => {

    const {id} = useParams()
    const [provinces, setProvinces] = useState([])
    const [selectedProvinceCode, setSelectedProvinceCode] = useState('')
    const [districts, setDistricts] = useState([])
    const [selectedDistrictCode, setSelectedDistrictCode] = useState('')
    const [wards, setWards] = useState([])
    const [selectedWardCode, setSelectedWardCode] = useState('')

    const [selectedStreet, setSelectedStreet] = useState('')
    const [selectedPrice, setselectedPrice] = useState('')
    const [selectedAcreage, setSelectedAcreage] = useState('')
    const [isLoading, setIsLoading] = useState(true)


    const [room, setRoom] = useState({
        "id": id,
        "address": "",
        "price": "",
        "provinceId": "",
        "districtId": "",
        "wardId": "",
        "acreage": "",
    })

    const navigate = useNavigate();

    useEffect(() => {
        getRoomById(id)
            .then( (room) => {
                if (room.status === 200) {
                
                    setSelectedProvinceCode(room.data.provinceId.code)
                    setSelectedDistrictCode(room.data.districtId.code)
                    setSelectedWardCode(room.data.wardId.code)
                    
                    setSelectedStreet(room.data.address)
                    setselectedPrice(room.data.price)
                    setSelectedAcreage(room.data.acreage)

                    setRoom((prevValues) => ({
                        ...prevValues,
                        "address": room.data.address,
                        "price": room.data.price,
                        "provinceId": room.data.provinceId.code,
                        "districtId": room.data.districtId.code,
                        "wardId": room.data.wardId.code,
                        "acreage": room.data.acreage
                    }))
                    setIsLoading(false)
                }
            })

    }, [])

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

    

    const handleProvinceChange = (event) => {
        setDistricts([])
        setWards([])
        setRoom((prevValues) => ({
            ...prevValues,
            "provinceId": event.target.value
        }))
        setSelectedProvinceCode(event.target.value)
    }

    const handleDistrictChange = (event) => {
        setWards([])
        setRoom((prevValues) => ({
            ...prevValues,
            "districtId": event.target.value
        }))
        setSelectedDistrictCode(event.target.value)
    }

    const handleWardChange = (event) => {
        setRoom((prevValues) => ({
            ...prevValues,
            "wardId": event.target.value
        }))
        setSelectedWardCode(event.target.value)
    }

    const handleStreetChange = (event) => {
        setRoom((prevValues) => ({
            ...prevValues,
            "address": event.target.value
        }))
        setSelectedStreet(event.target.value)
    }

    const handlePriceChange = (event) => {
        setRoom((prevValues) => ({
            ...prevValues,
            "price": event.target.value
        }))
        setselectedPrice(event.target.value)

    }

    const handleAcreageChange = (event) => {
        setRoom((prevValues) => ({
            ...prevValues,
            "acreage": event.target.value
        }))
        setSelectedAcreage(event.target.value)
    }

    const updateAction = async () => {
        await updateRoom(room)
        navigate('/quan-ly-nha-tro')
    }

    if (isLoading) 
        return <Loading/>

    return <>
        <div className="container">
            <h1 className="text-info text-center">Chỉnh sửa nhà trọ</h1>
            <div className="address">
                <h5 className='mt-2'>Thành phố/Tỉnh</h5>

                <select className="form-select" onChange={handleProvinceChange} value={selectedProvinceCode}>
                    <option value="">-- Chọn một tùy chọn --</option>
                    {
                        provinces.map((province, index) => {
                            return <option key={index} value={province.code}>{province.name}</option>
                        })
                    }

                </select>
                <h5 className='mt-2'>Quận/Huyện</h5>
                <select className="form-select" onChange={handleDistrictChange} value={selectedDistrictCode}>
                    <option value="">-- Chọn một tùy chọn --</option>
                    {
                        districts.map((district, index) => {
                            return <option key={index} value={district.code}>{district.name}</option>
                        })
                    }
                </select>
                <h5 className='mt-2'>Phường/Xã</h5>
                <select className="form-select" onChange={handleWardChange} value={selectedWardCode}>
                    <option value="">-- Chọn một tùy chọn --</option>
                    {
                        wards.map((ward, index) => {
                            return <option key={index} value={ward.code}>{ward.name}</option>
                        })
                    }
                </select>
                <div className="form-floating my-3">
                    <input type="text" className="form-control" id="house-number" placeholder="Số nhà" onChange={handleStreetChange} defaultValue={selectedStreet} />
                    <label htmlFor="house-number">Số nhà</label>
                </div>
            </div>
            <div className="form-floating my-3">
                <input type="number" className="form-control" id="house-price" placeholder="Giá tiền" onChange={handlePriceChange} defaultValue={selectedPrice} />
                <label htmlFor="house-price">Giá tiền</label>
            </div>
            <div className="form-floating my-3">
                <input type="number" className="form-control" id="house-acreage" placeholder="Diện tích" onChange={handleAcreageChange} defaultValue={selectedAcreage} />
                <label htmlFor="house-acreage">Diện tích</label>
            </div>
            <button className="btn btn-info" type="submit" onClick={updateAction}>Cập nhật</button>
        </div>
        
    </>
}
export default EditRoom