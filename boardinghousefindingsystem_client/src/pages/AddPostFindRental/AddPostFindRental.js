import { Editor } from "@tinymce/tinymce-react"
import Loading from "../../components/Loading/Loading"
import ImagePreview from "../../components/ImagePreview/ImagePreview"
import { VNDCurrencyFormat } from "../../services/Utils"
import { useState } from "react"
import { useRef } from "react"
import { addPostTental } from "../../services/ApiServices"
import { useNavigate } from "react-router-dom"
import { toast } from "react-toastify"

const AddPostFindRental = () => {

    const [isLoading, setIsLoading] = useState(false)
    const editorRef = useRef(null);
    const navigate = useNavigate()
    const processNotify = useRef(null)

    
    const handleFormSubmit = (evt) => {
        evt.preventDefault();
        processNotify.current = toast.loading("Quá trình đăng bài đang được xử lý...")
        setIsLoading(true)
        const dataForm = {
            "name": evt.target.name.value,
            "content": evt.target.content.value,
            "address": evt.target.address.value
        }
        addPostTental(dataForm)
        .then(res => {
            if (res.status === 201) {
                toast.update(processNotify.current, {
                    render: "Đăng bài thành công",
                    type: "success",
                    isLoading: false,
                    autoClose: 5000,
                    closeOnClick: true
                })
                setIsLoading(false)
                navigate("/tin-tim-phong-tro")
            }
            else if (res.response.status === 400) {
                const errors = res.response.data
                const errorsRes = []
                for (let key in errors) {
                    if (errors.hasOwnProperty(key))
                        errorsRes.push(...errors[key])
                }
                toast.update(processNotify.current, {
                    render: "Thêm phòng thất bại",
                    type: "error",
                    isLoading: false,
                    autoClose: 5000,
                    closeOnClick: true
                })
                errorsRes.forEach((error) => {
                    toast.error(error)
                })
                setIsLoading(false)
            }
        })
    }


    return <>
        <h1 className="text-center text-info">Đăng tin cho tìm phòng</h1>
        <div className="container">
            <form method="post" onSubmit={handleFormSubmit}>
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
                        initialValue=""
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
                            content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }',
                            encoding: 'raw',
                            entity_encoding: 'raw'
                        }}
                        apiKey="0mz34dd8eeink0dxfp3zxvup6y6q7m1u5jv0hupr5cb97w6l"
                    />
                </div>

                <div className="form-floating mb-3">
                    <input type="text" className="form-control" id="post-address" placeholder="Địa chỉ cần thuê" name="address" />
                    <label htmlFor="post-address">Phạm vi địa chỉ muốn thuê</label>
                </div>

                <div className="form-floating mb-3" style={{ textAlign: "center" }}>
                    <button type="submit" className="btn btn-info my-3" style={{ minWidth: "200px" }} disabled={isLoading} >
                        <span className="d-flex justify-content-center align-items-center gap-2">
                            <span>Đăng</span>
                            {
                                isLoading === true ?
                                    <Loading />
                                    : ""
                            }

                        </span>
                    </button>

                </div>
            </form>



        </div>
    </>
}

export default AddPostFindRental