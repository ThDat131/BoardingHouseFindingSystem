import { useEffect } from "react"
import { useParams } from "react-router-dom"
import { addComment, postRentalDetail } from "../../services/ApiServices"
import { useState } from "react"
import { useContext } from "react"
import { MyUserContext } from "../../App"
import DOMPurify from "dompurify"
import { useRef } from "react"
import { toast } from "react-toastify"
import Comment from "../../components/Comment/Comment"
import Loading from "../../components/Loading/Loading"

const DetailPostFindRental = () => {
    const [user, dispatch] = useContext(MyUserContext)
    const { id } = useParams()
    const [detailPost, setDetailPost] = useState(null)
    const [address, setAddress] = useState("")
    const [listComment, setListComment] = useState([])
    const [comment, setComment] = useState("")
    const proccessComment = useRef(null)
    const [isLoading, setIsLoading] = useState(true)


    useEffect(() => {
        postRentalDetail(id)
            .then((res) => {
                if (res.status === 200) {
                    // console.log(res.data)
                    setDetailPost(res.data)
                    setListComment(res.data.commentSet)
                    setIsLoading(false)
                }
            })
    }, [])

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
    if (isLoading)
        return <Loading />
    return <>
        <div className="container">
            <h1 className="text-center text-info my-3">Thông tin chi tiết</h1>

            <div className="d-flex align-items-center gap-3 my-3">
                <img className="rounded-circle" src={detailPost.username.avatar} alt="" width={40} height={40} />
                <p className="m-0">{detailPost.username.tentant.fullName}</p>
            </div>
            <h3 className="text-danger my-2">{detailPost.name}</h3>
            <div className="room-info-post d-flex gap-3 align-items-center mb-2">
                <div>
                    <p style={{ margin: "0" }}>Ngày đăng bài: {new Date(detailPost.createdDate).getDate()}/{new Date(detailPost.createdDate).getMonth() + 1}/{new Date(detailPost.createdDate).getFullYear()}</p>
                </div>
            </div>
            <div>
                <h3 className="my-2">Thông tin mô tả</h3>
                <div dangerouslySetInnerHTML={{ __html: DOMPurify.sanitize(detailPost.content, { USE_PROFILES: { html: true } }) }}></div>
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
                                <label htmlFor="floatingTextarea">Bình luận ở đây</label>
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

export default DetailPostFindRental