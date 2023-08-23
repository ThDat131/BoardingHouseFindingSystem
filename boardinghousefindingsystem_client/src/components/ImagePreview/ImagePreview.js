const ImagePreview = ({sourceFile}) => {
    return <>
        <img style={{ width: "280px", height: "200px", objectFit:"cover"}} src={sourceFile} alt="" />
    </>
}

export default ImagePreview