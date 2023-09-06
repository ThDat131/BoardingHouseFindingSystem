import { Link, useNavigate } from "react-router-dom"
import PostOfRoomRental from "../../components/PostOfRoomRental/PostOfRoomRental"
import "./RoomRental.css"
import AddPostRental from "../AddPostRental/AddPostRental"
import { useContext, useEffect, useState } from "react"
import { getAllPost } from "../../services/ApiServices"
import Loading from "../../components/Loading/Loading"
import { MyUserContext } from "../../App"

const RoomRental = () => {

    const [posts, setPosts] = useState(null)
    const [user, dispatch] = useContext(MyUserContext);

    const navigate = useNavigate();
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
            {user === null || user.role !== 0 ? "" : <button className="btn btn-info my-2">Đăng tin cho thuê phòng</button> }
                
            <div className="row">
                <div className="col-8">
                    <h2>{posts.length} tin cho thuê phòng trọ</h2>
                    <div className="posts">
                        {
                            posts.map((data, index) => {
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