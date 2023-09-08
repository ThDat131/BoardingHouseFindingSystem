import { useEffect, useState } from 'react'
import './LocationModal.css'
import { loadAllDistrictsByProvinceCode, loadAllProvinces, loadAllWardssByDistrictCode } from '../../services/ApiServices'
import Loading from '../Loading/Loading'
const LocationModal = ({isOpen, onClose, addressValue, addressData}) => {  


    const [provinces, setProvinces] = useState([])
    const [selectedProvinceCode, setselectedProvinceCode] = useState('')
    const [districts, setDistricts] = useState([])
    const [selectedDistrictCode, setSelectedDistrictCode] = useState('')
    const [wards, setWards] = useState([])
    const [selectedWardCode, setSelectedWardCode] = useState('')
    const [isLoading, setIsLoading] = useState(true)
    useEffect (() => {
        loadAllProvinces()
        .then(res => {
            setProvinces(res.data)
            setIsLoading(false)
        })
        .catch(ex => console.error(ex))
    }, [])

    useEffect(() => {
        if (selectedProvinceCode !== ''){
            loadAllDistrictsByProvinceCode(selectedProvinceCode)
            .then(res => setDistricts(res.data))
        }
    },[selectedProvinceCode])

    useEffect(() => {
        if (selectedDistrictCode !== '') {
            loadAllWardssByDistrictCode(selectedDistrictCode)
            .then(res => setWards(res.data))
        }
    }, [selectedDistrictCode])

    const handleProvinceChange = (event) => {
        setselectedProvinceCode(event.target.value)
        setDistricts([])
        setWards([])
    }

    const handleDistrictChange = (event) => {
        setSelectedDistrictCode(event.target.value)
        setWards([])

    }

    const handleWardChange = (event) => {
        setSelectedWardCode(event.target.value)

    }

    const submitValue = () => {
        let addressString = ''
        provinces.forEach((province) => {
            if (selectedProvinceCode === province.code) {
                addressString += province.fullName + ', '
            }
        })
        districts.forEach((district) => {
            if (selectedDistrictCode === district.code) {
                addressString += district.fullName + ', '
            }
        })
        wards.forEach((ward) => {
            if (selectedWardCode === ward.code) {
                addressString += ward.fullName
            }
        })
        addressValue(addressString)
        addressData({
            "province_id": selectedProvinceCode,
            "district_id": selectedDistrictCode,
            "ward_id": selectedWardCode
        })
        onClose()

    }

    return <>
        <div className={`modal fade show ${isOpen ? 'showUp' : ''}`} tabIndex="-1" >
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Chọn địa điểm</h5>
                        <button type="button" className="btn-close" onClick={onClose}></button>
                    </div>
                    <div className="modal-body">
                        {
                            isLoading ? <Loading /> : 
                            <>
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
                            </>
                        }

                        

                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                        <button type="button" className="btn btn-primary" onClick={submitValue}>Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}
export default LocationModal