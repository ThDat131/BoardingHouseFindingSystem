import { useState } from "react"
import LocationModal from "../../components/LocationModal/LocationModal"
import './Main.css'
import PriceModal from "../../components/PriceModal/PriceModal"
import PostOfMain from "../../components/PostOfMain/PostOfMain"
import { useEffect } from "react"
import { getAllPost } from "../../services/ApiServices"
import Loading from "../../components/Loading/Loading"
import PostOfRoomRental from "../../components/PostOfRoomRental/PostOfRoomRental"

const Main = () => {

    const [addressValue, setAddressValue] = useState('Toàn quốc')
    const [minPriceValue, setMinPriceValue] = useState(0)
    const [maxPriceValue, setMaxPriceValue] = useState(0)
    const [isLocationModalOpen, setIsLocationModalOpen] = useState(false)
    const [isPriceModalOpen, setIsPriceModalOpen] = useState(false)
    const [posts, setPosts] = useState(null)
    const [isLoading, setIsLoading] = useState(true)
    const [requestParams, setRequestParams] = useState("")
    const [requestPriceParams, setRequestPriceParams] = useState("")
    const [requestAddressParams, setRequestAddressParams] = useState("")
    const [searching, setSearching] = useState(false);

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

    const handlePriceValue = (value) => {
        setRequestPriceParams("")
        for (const key in value) {
            if (value.hasOwnProperty(key)) {
                setRequestPriceParams(prevValue => prevValue + key + "=" + value[key] + "&")
            }
            if (key === "fromPrice") 
                setMinPriceValue(value[key])
            else if (key === "toPrice")
                setMaxPriceValue(value[key])
        }
    }

    const handleAddressData = (value) => {
        setRequestAddressParams("")
        for (const key in value) {
            if (value.hasOwnProperty(key)) {
                setRequestAddressParams(prevValue => prevValue + key + "=" + value[key] + "&")
            }
        }
    }

    useEffect(() => {
        const request = requestAddressParams + requestPriceParams
        getAllPost(request)
            .then(res => {
                if (res.status === 200) {
                    setPosts(res.data)
                    setIsLoading(false)

                }
            })
    }, [searching])

    const handleSearch = () => {
        setSearching(!searching)

    }

    if (isLoading)
        return <Loading />

    return <>
        <LocationModal isOpen={isLocationModalOpen} onClose={closeLocationModal} addressValue={handleAddressValue} addressData={handleAddressData} />
        <PriceModal isOpen={isPriceModalOpen} onClose={closePriceModal} priceValue={handlePriceValue} />

        <div className="container">
            <div className="row">
                <div className="bg-warning d-flex justify-content-center align-items-center gap-2 p-3">
                    <div style={{ cursor: 'pointer' }} className="search-item col-3 p-2 bg-light" onClick={openLocationModal}>
                        <span>{addressValue === "" ? "Toàn quốc" : addressValue}</span>
                    </div>
                    <div style={{ cursor: 'pointer' }} className="search-item  col-3 p-2 bg-light" onClick={openPriceModal}>
                        <span>{minPriceValue !== 0 || maxPriceValue !== 0 ? `${minPriceValue} - ${maxPriceValue}` : 'Chọn giá'}</span>
                    </div>
                    <div className="search-item col-3 p-2 btn btn-info" onClick={handleSearch}>
                        <span>Tìm kiếm</span>
                    </div>
                </div>

                <div className="posts-section col-10">
                    <div className="header">
                        <h2>Tin mới nhất</h2>
                    </div>

                    <div className="body">
                        <div className="row">
                            {
                                posts.length <= 0 ? <h1>Không có nhà trọ nào</h1> : 
                                    posts.map((data, index) => {
                                        return <div className="col-8 my-2">
                                            <PostOfRoomRental key={index} postData={data} />
                                        </div>
                                    })
                            }
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </>
}

export default Main