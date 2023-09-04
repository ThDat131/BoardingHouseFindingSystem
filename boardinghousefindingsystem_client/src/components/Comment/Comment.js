const Comment = ({comment}) => {
    return <>
        <div className="comment-section d-flex flex-start my-3 flex-wrap w-100">
            <img className="rounded-circle shadow-1-strong me-3"
                src={comment.username.avatar} alt="avatar" width="65"
                height="65" />
            <div className="flex-grow-1 flex-shrink-1">
                <div className="d-flex justify-content-between align-items-center">
                    <p className="mb-1" style={{ fontWeight: 600 }}>
                        {comment.username.role === 0 ? comment.username.landLord.fullName : (comment.username.role === -1 ? comment.username.tentant.fullName : comment.username.username)}</p>
                </div>
                <p className="small mb-0">
                    {comment.content}
                </p>
            </div>
        </div>
    </>
    
}

export default Comment