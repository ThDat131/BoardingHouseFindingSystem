import { useEffect, useState } from "react"
import { getLatLngAddress, postRentalDetail } from "../../services/ApiServices"
import { useParams } from "react-router-dom"
import Loading from "../../components/Loading/Loading"
import { VNDCurrencyFormat } from "../../services/Utils"
import DOMPurify from "dompurify"
import Map from "../../components/Map/Map"

const DetailPostRental = () => {

    

    const {id} = useParams()
    const [detailPost, setDetailPost] = useState(null)
    const [imgPos, setImgPos] = useState(0)
    const [isLoading, setIsLoading] = useState(true)

    const [pos, setPos] =  useState({ lat: 0, lng: 0 })
    const [address, setAddress] = useState("")
    const [viewport, setViewport] = useState({
        width: 800,
        height: 600,
        latitude: 37.78,
        longitude: -122.45,
        zoom: 14
    });


    // const htmlFormatted = DOMPurify.sanitize(detailPost.content, {
    //     USE_PROFILES: { html: true },
    // });

    const clickNext = () => {
        setImgPos((prevImgPos) => (prevImgPos + 1) % detailPost.imageSet.length)
    }
    const clickPrev = () => {
        setImgPos(prevImgPos => (prevImgPos - 1 + detailPost.imageSet.length) % detailPost.imageSet.length )
    }

    useEffect(() => {
        postRentalDetail(id)
        .then(async (res) => {
            if (res.status === 200){
                console.log(res.data)
                setDetailPost(res.data)
                const currentAddress = (res.data.roomId + ", " + res.data.roomId.wardId.fullName + ", " + res.data.roomId.districtId.fullName + ", " + res.data.roomId.provinceId.fullName )
                setAddress(res.data.roomId.address)
                getLatLngAddress(currentAddress)
                .then((res) => {
                    setPos(res.data.results[0].geometry.location)
                    console.log(res.data)
                    setIsLoading(false)
                })
            }
        })
    }, [])


    if (isLoading) 
        return <Loading />
    return <>
        <div className="container">
            <h1 className="text-center text-info">Thông tin chi tiết</h1>
            <div className="carousel slide" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button type="button" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div className="carousel-inner">
                    {detailPost.imageSet.map((img, index) => (
                        <div className={`carousel-item ${index === imgPos ? 'active' : ''}`} key={index} >
                            <img src={img.url} className="d-block w-100" alt="" style={{objectFit: "cover", width: "100%", height:"400px"}} />
                        </div>
                    ))}
                </div>
                <button className="carousel-control-prev" type="button" data-bs-slide="prev" onClick={clickPrev}>
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-slide="next" onClick={clickNext}>
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>

            <h3 className="text-danger">{detailPost.name}</h3>
            <p>Địa chỉ: {detailPost.roomId.address}, {detailPost.roomId.wardId.fullName}, {detailPost.roomId.districtId.fullName}, {detailPost.roomId.provinceId.fullName}</p>
            <div className="room-info-post d-flex gap-3 align-items-center">
                <div className="price">
                    <h4 className="text-success">{VNDCurrencyFormat.format(detailPost.roomId.price)}/tháng</h4>
                </div>
                <div className="acreage">
                    <p>{detailPost.roomId.acreage} m<sup>2</sup></p>
                </div>
                <div>
                    <p>Ngày đăng bài: {new Date(detailPost.createdDate).getDate()}/{new Date(detailPost.createdDate).getMonth()+1}/{new Date(detailPost.createdDate).getFullYear()}</p>
                </div>
            </div>
            <div>
                <h3>Thông tin mô tả</h3>
                <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(detailPost.content, { USE_PROFILES: { html: true } }) }}></div>
            </div>
            <div>
                <h3>Vị trí</h3>
                <div className="w-100 h-100" >
                    <Map lat={pos.lat} lng={pos.lng}/>
                </div>
                
            </div>
        </div>
        
    </>
}

export default DetailPostRental