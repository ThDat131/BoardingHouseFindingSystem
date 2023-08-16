import { useState } from "react"
import LocationModal from "../../components/LocationModal/LocationModal"
import './Main.css'
import PriceModal from "../../components/PriceModal/PriceModal"
import PostOfMain from "../../components/PostOfMain/PostOfMain"

const Main = () => {

    const [addressValue, setAddressValue] = useState('Toàn quốc')
    const [minPriceValue, setMinPriceValue] = useState(0)
    const [maxPriceValue, setMaxPriceValue] = useState(0)
    const [isLocationModalOpen, setIsLocationModalOpen] = useState(false)
    const [isPriceModalOpen, setIsPriceModalOpen] = useState(false)

    const openLocationModal = () => {
        setIsLocationModalOpen(true)
    }
    const closeLocationModal = () => {
        setIsLocationModalOpen(false)
    }

    const openPriceModal = () => {
        setIsPriceModalOpen(true)
    }
    const closePriceModal = () => {
        setIsPriceModalOpen(false)
    }

    const handleAddressValue = (value) => {
        setAddressValue(value)
    }

    const handleMinPriceValue = (value) => {
        setMinPriceValue(value)
    }

    const handleMaxPriceValue = (value) => {
        setMaxPriceValue(value)
    }

    return <>
        <LocationModal isOpen={isLocationModalOpen} onClose={closeLocationModal} addressValue={handleAddressValue}/>
        <PriceModal isOpen={isPriceModalOpen} onClose={closePriceModal} 
        minPriceValue={handleMinPriceValue} maxPriceValue={handleMaxPriceValue}/>
        
        <div className="container">
            <div className="row">
                <div className="bg-warning d-flex justify-content-center align-items-center gap-2 p-3">
                    <div style={{ cursor: 'pointer' }} className="search-item col-3 p-2 bg-light" onClick={openLocationModal}>
                        <span>{addressValue}</span>
                    </div>
                    <div style={{ cursor: 'pointer' }} className="search-item  col-3 p-2 bg-light" onClick={openPriceModal}>
                        <span>{minPriceValue !== 0 || maxPriceValue !== 0 ? `${minPriceValue} - ${maxPriceValue}` : 'Chọn giá'}</span>
                    </div>
                    <div className="search-item col-3 p-2 btn btn-info">
                        <span>Tìm kiếm</span>
                    </div>
                </div>
                
                <div className="posts-section col-10">
                    <div className="header">
                        <h2>Tin mới nhất</h2>
                    </div>

                    <div className="body">
                        <div className="w-100">
                            <div className="col-4">
                                <PostOfMain />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </>
}

export default Main