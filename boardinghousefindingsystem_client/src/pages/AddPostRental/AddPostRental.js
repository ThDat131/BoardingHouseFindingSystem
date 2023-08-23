import { useEffect, useRef } from "react"
import { useState } from "react"
import { addPostRentalRoom, loadAllRoomByUsername } from "../../services/ApiServices"
import Loading from "../../components/Loading/Loading"
import "./AddPostRental.css"
import ImagePreview from "../../components/ImagePreview/ImagePreview"
import { Editor } from "@tinymce/tinymce-react"
import { VNDCurrencyFormat } from "../../services/Utils"
import { useNavigate } from "react-router-dom"

const AddPostRental = () => {

    const [rooms, setRooms] = useState([])

    const [imageUrls, setImageUrls] = useState([])

    const [isLoading, setIsLoading] = useState(false)

    const filesRef = useRef([])

    const editorRef = useRef(null);

    const navigate = useNavigate();


    useEffect(() => {
        loadAllRoomByUsername('landlord2')
            .then(res => {
                setRooms(res.data)
                setIsLoading(false)
            })
    }, [])

    const handleChangeFiles = (evt) => {
        if (evt.target.files) {
            setImageUrls([])
            filesRef.current = []
            for (let file of evt.target.files) {
                // let reader = new FileReader()
                // reader.onload = (e) => {
                //     setImages(prevImages => [...prevImages, e.target.result])
                // }
                // reader.readAsDataURL(file)

                filesRef.current = [...filesRef.current, file]

                setImageUrls(prevImages => [...prevImages, URL.createObjectURL(file)])
            }
        }
    }

    const handleFormSubmit = async (evt) => {
        evt.preventDefault();
        setIsLoading(true)
        const name = evt.target.name.value
        const content = evt.target.content.value
        const username = "landlord2"
        const roomId = evt.target.roomId.value 
        const formData = new FormData()
        formData.append("name", name)
        formData.append("content", content)
        formData.append("username", username)
        formData.append("roomId", roomId)
        for(let i = 0; i < filesRef.current.length; i++) {
            formData.append("files", filesRef.current[i])
        }
        await addPostRentalRoom(formData).then(() => {
            setIsLoading(false)
            navigate("/tin-tim-nha-tro")
        })
    }

    return <>
        <h1 className="text-center text-info">Đăng tin cho thuê phòng</h1>
        <div className="container">
            <form method="post" onSubmit={handleFormSubmit} encType="multipart/form-data">
                <div className="form-floating mb-3">
                    <input type="text" className="form-control" id="post-name" placeholder="Tên bài đăng" name="name" />
                    <label htmlFor="post-name">Tên bài đăng</label>
                </div>
                <div className="mb-3">
                    {/* <label htmlFor="post-content" className="form-label">Nội dung bài đăng</label>
                    <textarea style={{ resize: "none" }} className="form-control" id="post-content" rows="10" cols="10" name="content"></textarea> */}
                    <Editor
                        textareaName="content"
                        onInit={(evt, editor) => editorRef.current = editor}
                        initialValue="<p>This is the initial content of the editor.</p>"
                        init={{
                            height: 500,
                            menubar: false,
                            plugins: [
                                'advlist autolink lists link image charmap print preview anchor',
                                'searchreplace visualblocks code fullscreen',
                                'insertdatetime media table paste code help wordcount'
                            ],
                            toolbar: 'undo redo | formatselect | ' +
                                'bold italic backcolor | alignleft aligncenter ' +
                                'alignright alignjustify | bullist numlist outdent indent | ' +
                                'removeformat | help',
                            content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
                        }}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="room-select" className="form-label">Phòng trọ</label>
                    <select className="form-select" name="roomId" id="room-select">
                        <option value="">--Chọn phòng---</option>
                        {
                            isLoading ? <Loading /> :
                                rooms.map((room, index) => {
                                    return <option key={index} value={room.id}>{room.address} - {VNDCurrencyFormat.format(room.price)} - {room.acreage} <span>m<sup>2</sup></span></option>
                                })
                        }
                    </select>
                </div>

                <div className="input-section mb-3">
                    <input type="file" name="files" className="form-control" id="post-images" multiple onChange={handleChangeFiles}  />
                    <label htmlFor="post-images" className="form-label mb-3"><i className="fa-solid fa-upload"></i> &nbsp; Ảnh bài đăng</label>

                    <div className="images-preview d-flex align-items-center justify-content-center gap-2">
                        {
                            imageUrls.length > 0 ? imageUrls.map((image, index) => {
                                return <ImagePreview key={index} sourceFile={image} />
                            }) : ""
                        }
                    </div>
                </div>

                <div className="form-floating mb-3" style={{ textAlign: "center" }}>
                    <button type="submit" className="btn btn-info my-3" style={{ minWidth: "200px" }}>
                        <span className="d-flex justify-content-center align-items-center gap-2">
                            <span>Đăng</span>
                            {
                                isLoading === true ?
                                <div className="spinner-border" role="status">
                                    <span className="sr-only">Loading...</span>
                                </div> 
                                : ""
                            }
                            
                        </span>
                    </button>

                </div>
            </form>
            
            
            
        </div>

        
    </>
}
export default AddPostRental