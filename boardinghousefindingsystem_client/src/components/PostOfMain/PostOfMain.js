import './PostOfMain.css'

const PostOfMain = () => {
    return <>
        <div className='post rounded overflow-hidden'>
            <div className='img-post'>
                <img className='w-100' src="http://media.phongtot.vn/xc5tx4cj/phong-tro-mini-thumb-resized-fqizc.jpeg" alt="" />
            </div>
            <div className="img-header p-2">
                <h3>Phong tro abc</h3>
            </div>
            <div className='img-desc d-flex align-items-center justify-content-between p-2'>
                <p>Tan Binh</p>
                <p>5.5m</p>
            </div>
        </div>
    </>
}
export default PostOfMain