import { Link } from "react-router-dom"
import { VNDCurrencyFormat, decodeHtmlEntities } from "../../services/Utils"

const PostOfFindingRoomRental = ({postData}) => {
    return <>
        <Link to={`/tin-tim-phong-tro/${postData.id}`} style={{ color: '#000', textDecoration: 'none' }}> 
            <div className="post d-flex justify-content-between align-items-center p-3 mb-3 rounded">
                <div className="info-post col-12 p-4">
                    <div className="name-post">
                        <h3>{postData.name}</h3>
                    </div>
                    <div className="room-info-post d-flex justify-content-between">
                    </div>
                    <div className="content-post">
                        {decodeHtmlEntities(postData.content)}
                    </div>
                    <div className="owner-post d-flex justify-content-between align-items-center mt-3">
                        <div className="info-owner d-flex">
                            <div className="avatar-owner me-2">
                                <img className="w-100 rounded-circle" src={postData.username.avatar} alt="" width={30} height={30} />
                            </div>
                            <div className="name-owner">
                                <p className="m-0">{postData.username.tentant.fullName}</p>
                            </div>
                        </div>
                        <div className="contact-owner d-flex flex-column gap-2">
                            <div className="zalo btn btn-info">
                                <p className="m-0">Nhắn Zalo</p>
                            </div>
                            <div className="mobile btn btn-danger">
                                <p className="m-0">Gọi {postData.username.tentant.phone}</p>
                            </div>
                        </div>
                    </div>

                </div>


            </div>
        </Link>
    </>
}

export default PostOfFindingRoomRental