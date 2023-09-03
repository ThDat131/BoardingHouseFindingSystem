import "./PostOfRoomRental.css"
import DOMPurify from "dompurify";
import parse from "html-react-parser";
import { VNDCurrencyFormat, decodeHtmlEntities } from "../../services/Utils";
import { Link } from "react-router-dom";

const PostOfRoomRental = ({postData}) => {

    const imageUrls = postData.imageSet.map(image => image.url)

    // const htmlFormatted = DOMPurify.sanitize(postData.content, {
    //     USE_PROFILES: { html: true },
    // });



    return <>
        <Link to={`/tin-tim-nha-tro/${postData.id}`} style={{color: '#000', textDecoration:'none'}}> 
            <div className="post d-flex justify-content-between align-items-center p-3 mb-3 rounded">
                <div className="img-post col-4">
                    <img className="w-100" src={imageUrls[0]} alt="" />
                </div>
                <div className="info-post col-8 p-4">
                    <div className="name-post">
                        <h3>{postData.name}</h3>
                    </div>
                    <div className="room-info-post d-flex justify-content-between">
                        <div className="price">
                            <p className="text-success">{VNDCurrencyFormat.format(postData.roomId.price)}</p>
                        </div>
                        <div className="acreage">
                            {postData.roomId.acreage} m<sup>2</sup>
                        </div>
                        <div className="address">
                            {postData.roomId.districtId.fullName + ", " + postData.roomId.provinceId.fullName} 
                        </div>
                    </div>
                    <div className="content-post">
                        {decodeHtmlEntities(postData.content)}
                    </div>
                    <div className="owner-post d-flex justify-content-between align-items-center mt-3">
                        <div className="info-owner d-flex">
                            <div className="avatar-owner me-2">
                                <img className="w-100 rounded-circle" src={postData.username.avatar} alt="" width={30} height={30}/>
                            </div>
                            <div className="name-owner">
                                <p className="m-0">{postData.username.username}</p>
                            </div>
                        </div>
                        <div className="contact-owner d-flex flex-column gap-2">
                            <div className="zalo btn btn-info">
                                <p className="m-0">Nhắn Zalo</p>
                            </div>
                            <div className="mobile btn btn-danger">
                                <p className="m-0">Gọi 0768785291</p>
                            </div>
                        </div>
                    </div>

                </div>

                
            </div>
        </Link>
    </>
}

export default PostOfRoomRental