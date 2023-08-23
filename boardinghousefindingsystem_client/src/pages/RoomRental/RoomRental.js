import { Link } from "react-router-dom"
import PostOfRoomRental from "../../components/PostOfRoomRental/PostOfRoomRental"
import "./RoomRental.css"
import AddPostRental from "../AddPostRental/AddPostRental"
import { useEffect, useState } from "react"
import { getAllPost } from "../../services/ApiServices"
import Loading from "../../components/Loading/Loading"
const RoomRental = () => {

    const [posts, setPosts] = useState(null)
    useEffect(() => {
        getAllPost()
        .then(res => {
            setPosts(res.data)
        })
        .catch((ex) => {
            console.log(ex)
        })
    }, [])

    if (posts === null) 
        return <Loading/>
    return <>
        <div className="container">
            <Link to={'/dang-tin-cho-thue'}>
                <button className="btn btn-info my-2">Đăng tin cho thuê phòng</button>
                
            </Link>
            <div className="row">
                <div className="col-8">
                    <h2>{posts.length} tin cho thuê phòng trọ</h2>
                    <div className="posts">
                        {
                            posts.map((data, index) => {
                                console.log(data)
                                return <PostOfRoomRental key={index} postData={data}/>
                            })
                        }

                    </div>
                </div>
                

                <div className="col-4">

                </div>
            </div>
        </div>
    </>
}
export default RoomRental