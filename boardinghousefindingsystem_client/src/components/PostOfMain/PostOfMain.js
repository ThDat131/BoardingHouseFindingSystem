import './PostOfMain.css'

const PostOfMain = ({data}) => {
    return <>
        <div className='post rounded overflow-hidden'>
            <div className='img-post'>
                <img className='w-100' src={data.imageSet[0].url} alt="" />
            </div>
            <div className="img-header p-2 d-inline-block">
                <h3>{data.name}</h3>
            </div>
            <div className='img-desc d-flex align-items-center justify-content-between p-2'>
                <p>{data.roomId.districtId.fullName + ", " + data.roomId.provinceId.fullName} </p>
                <p>{data.roomId.acreage} m<sup>2</sup></p>
            </div>
        </div>
    </>
}
export default PostOfMain