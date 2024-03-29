import { useContext, useEffect, useState } from "react"
import { addComment, addFollow, deleteFollow, getCurrentUser, getLatLngAddress, postRentalDetail } from "../../services/ApiServices"
import { useParams } from "react-router-dom"
import Loading from "../../components/Loading/Loading"
import { VNDCurrencyFormat } from "../../services/Utils"
import DOMPurify from "dompurify"
import Map from "../../components/Map/Map"
import { MyUserContext } from "../../App"
import { toast } from "react-toastify"
import Comment from "../../components/Comment/Comment"
import { useRef } from "react"
import cookie from "react-cookies";


const DetailPostRental = () => {


    const [user, dispatch] = useContext(MyUserContext)
    const { id } = useParams()
    const [detailPost, setDetailPost] = useState(null)
    const [imgPos, setImgPos] = useState(0)
    const [isLoading, setIsLoading] = useState(true)
    const [comment, setComment] = useState("")
    const [listComment, setListComment] = useState([])
    const [listFollow, setListFollow] = useState([])
    const [isFollow, setIsFollow] = useState(null)

    const [pos, setPos] = useState({ lat: 0, lng: 0 })
    const [address, setAddress] = useState("")

    const proccessComment = useRef(null)
    const proccessFollow = useRef(null)
    let flag = false;


    // const htmlFormatted = DOMPurify.sanitize(detailPost.content, {
    //     USE_PROFILES: { html: true },
    // });

    const clickNext = () => {
        setImgPos((prevImgPos) => (prevImgPos + 1) % detailPost.imageSet.length)
    }
    const clickPrev = () => {
        setImgPos(prevImgPos => (prevImgPos - 1 + detailPost.imageSet.length) % detailPost.imageSet.length)
    }


    useEffect(() => {
        postRentalDetail(id)
            .then((res) => {
                if (res.status === 200) {
                    // console.log(res.data)
                    setDetailPost(res.data)
                    const currentAddress = (res.data.roomId + ", " + res.data.roomId.wardId.fullName + ", " + res.data.roomId.districtId.fullName + ", " + res.data.roomId.provinceId.fullName)
                    setAddress(res.data.roomId.address)
                    getLatLngAddress(currentAddress)
                        .then((res) => {
                            setPos(res.data.results[0].geometry.location)
                            setIsLoading(false)
                        })
                    setListComment(res.data.commentSet)
                }
            })
    }, [])

    useEffect(() => {
        if (user !== null) {
            if (user.role === -1) {
                setListFollow(user.tentant.followSet)
            }
        }
    }, [user])

    useEffect(() => {
        if (listFollow && detailPost) {
            const isFollowing = listFollow.some(follow => follow.landLordId.id === detailPost.username.landLord.id);
            setIsFollow(isFollowing);
        }
    }, [listFollow, detailPost]);

    const postComment = () => {
        if (user === null) {
            toast.error("Vui lòng đăng nhập để bình luận!")
            return;
        }
        let data = {
            "content": comment,
            "postId": id,
        }
        proccessComment.current = toast.loading("Đang gửi bình luận...")
        addComment(data)
            .then(res => {
                if (res.status === 201) {
                    setListComment(prevListComment => [...prevListComment, res.data])
                    toast.update(proccessComment.current, {
                        render: "Bình luận thành công",
                        type: "success",
                        isLoading: false,
                        autoClose: 5000,
                        closeOnClick: true
                    })
                }
            })
        setComment("")
    }

    const handleFollow = () => {
        if (flag) {
            toast.error("Vui lòng chờ")
            return
        }
        if (!flag) {
            flag = true
            proccessFollow.current = toast.loading("Đang xử lý tình trạng theo dõi...")
            addFollow(detailPost.username.landLord.id)
                .then(res => {
                    if (res.status === 201) {
                        toast.update(proccessFollow.current, {
                            render: `Theo dõi ${res.data.landLordId.fullName} thành công`,
                            type: "success",
                            isLoading: false,
                            autoClose: 5000,
                            closeOnClick: true
                        })
                        getCurrentUser()
                            .then(res => {
                                if (res.status === 200) {
                                    let user = res.data
                                    cookie.save("user", user);
                                    dispatch({
                                        "type": "update_user",
                                        "payload": user
                                    })
                                    flag = true
                                }
                            })
                    }
                })
        }

        // console.log(listFollow)
    }

    const handleUnFollow = () => {
        if (flag) {
            toast.error("Vui lòng chờ")
            return
        }
        if (!flag) {
            flag = true
            proccessFollow.current = toast.loading("Đang xử lý tình trạng theo dõi...")
            deleteFollow(detailPost.username.landLord.id)
                .then(res => {
                    if (res.status === 204) {
                        toast.update(proccessFollow.current, {
                            render: `Huỷ theo dõi ${detailPost.username.landLord.fullName} thành công`,
                            type: "success",
                            isLoading: false,
                            autoClose: 5000,
                            closeOnClick: true
                        })
                        getCurrentUser()
                            .then(res => {
                                if (res.status === 200) {
                                    let user = res.data
                                    cookie.save("user", user);
                                    dispatch({
                                        "type": "update_user",
                                        "payload": user
                                    })
                                    flag = false
                                }
                            })
                    }
                })
        }

    }

    if (isLoading)
        return <Loading />
    return <>
        <div className="container">
            <h1 className="text-center text-info my-3">Thông tin chi tiết</h1>
            <div className="carousel slide my-3" data-bs-ride="carousel">
                <div className="carousel-inner">
                    {detailPost.imageSet.map((img, index) => (
                        <div className={`carousel-item ${index === imgPos ? 'active' : ''}`} key={index} >
                            <img src={img.url} className="d-block w-100" alt="" style={{ objectFit: "cover", width: "100%", height: "300px" }} />
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

            <div className="d-flex align-items-center gap-3 my-3">
                <img className="rounded-circle" src={detailPost.username.avatar} alt="" width={40} height={40} />
                <p className="m-0">{detailPost.username.landLord.fullName}</p>
                {/* <button type="button" className="btn btn-info" onClick={handleFollow}>Theo dõi</button> */}
                {
                    user !== null && user.role === -1 ?
                    (isFollow
                        ?
                        <>
                            <button type="button" className="btn btn-success">Đã theo dõi</button>
                            <button type="button" className="btn btn-danger" onClick={handleUnFollow}>Huỷ theo dõi</button>
                        </>
                        : <button type="button" className="btn btn-success" onClick={handleFollow}>Theo dõi</button>)
                    : ""
                }
            </div>
            <h3 className="text-danger my-2">{detailPost.name}</h3>
            <p>Địa chỉ: {detailPost.roomId.address}, {detailPost.roomId.wardId.fullName}, {detailPost.roomId.districtId.fullName}, {detailPost.roomId.provinceId.fullName}</p>
            <div className="room-info-post d-flex gap-3 align-items-center mb-2">
                <div className="price">
                    <h4 className="text-success" style={{ margin: "0" }}>{VNDCurrencyFormat.format(detailPost.roomId.price)}/tháng</h4>
                </div>
                <div className="acreage">
                    <p style={{ margin: "0" }}>{detailPost.roomId.acreage} m<sup>2</sup></p>
                </div>
                <div>
                    <p style={{ margin: "0" }}>Ngày đăng bài: {new Date(detailPost.createdDate).getDate()}/{new Date(detailPost.createdDate).getMonth() + 1}/{new Date(detailPost.createdDate).getFullYear()}</p>
                </div>
            </div>
            <div>
                <h3 className="my-2">Thông tin mô tả</h3>
                <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(detailPost.content, { USE_PROFILES: { html: true } }) }}></div>
            </div>
            <div>
                <h3 className="my-2">Vị trí</h3>
                <div className="w-100 h-100 my-3" >
                    <Map lat={pos.lat} lng={pos.lng} />
                </div>
            </div>
            <div>
                <h3 className="my-2">Bình luận</h3>
                <div className="row d-flex justify-content-start my-3">
                    <div className="col-md-12 col-lg-10 col-xl-8">
                        <div className="d-flex flex-start w-100">
                            {
                                user === null ? <img className="rounded-circle shadow-1-strong me-3"
                                    src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Font_Awesome_5_solid_user.svg/1792px-Font_Awesome_5_solid_user.svg.png" alt="avatar" width="40"
                                    height="40" />
                                    :
                                    <img className="rounded-circle shadow-1-strong me-3"
                                        src={user.avatar} alt="avatar" width="40"
                                        height="40" />
                            }

                            <div className="form-floating w-100">
                                <textarea className="form-control" placeholder="Leave a comment here" id="floatingTextarea" onChange={e => setComment(e.target.value)} value={comment}></textarea>
                                <label htmlFor="floatingTextarea">Đánh giá về nhà trọ ở đây</label>
                            </div>
                        </div>
                        <div className="float-end mt-2 pt-1">
                            <button type="button" className="btn btn-primary btn" onClick={postComment}>Bình luận</button>
                        </div>
                    </div>
                </div>
                <div className="d-flex flex-start my-3 flex-wrap">
                    {
                        listComment.length >= 1 ?
                            listComment.map((comment, index) => {
                                return <Comment key={index} comment={comment} />
                            })
                            : <div className="alert alert-danger">Chưa có bình luận</div>
                    }

                </div>

            </div>
        </div>

    </>
}

export default DetailPostRental