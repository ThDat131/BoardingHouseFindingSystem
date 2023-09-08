import { useContext } from "react"
import { MyUserContext } from "../../App"
import { Link } from "react-router-dom"
import { useEffect } from "react"
import { getPostsTentant } from "../../services/ApiServices"
import { useState } from "react"
import PostOfFindingRoomRental from "../../components/PostOfFindingRoomRental/PostOfFindingRoomRental"
import Loading from "../../components/Loading/Loading"

const FindRoomRental = () => {

    const [user, dispatch] = useContext(MyUserContext)
    const [posts, setPosts] = useState(null)
    const [isLoading, setIsLoading] = useState(true)

    useEffect(() => {
        getPostsTentant()
        .then((res) => {
            setPosts(res.data)
            setIsLoading(false)
        })
    }, [])

    if (posts === null) 
        return <Loading />
    
    return <>
        <div className="container">
            <div className="row">
                <div className="col-8">
                    <h2>{posts.length} tin tìm phòng trọ</h2>
                    {user === null || user.role !== -1 ? "" : <Link to='/dang-tin-tim-phong' ><button className="btn btn-info my-2">Đăng tin tìm phòng</button></Link>}
                    <div className="posts">
                        {
                            posts.map((data, index) => {
                                return <PostOfFindingRoomRental key={index} postData={data} />
                            })
                        }

                    </div>

                </div>
            </div>
        </div>
    </>
}
export default FindRoomRental