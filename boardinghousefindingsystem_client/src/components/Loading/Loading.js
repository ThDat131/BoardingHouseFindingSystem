const Loading = () => {
    // const options = {
    //     animationData:animationLoading,
    //     loop: true
    // };
    

    // const { View } = useLottie(options);
    // return <>
    //     <div style={{width: '100px', height: '100px'}}>
    //         {View}
    //     </div>
        
    // </>
    return <>
        <div className="text-center">
            <div className="spinner-border" role="status">
                <span className="sr-only">Loading...</span>
            </div>
        </div>
    </>
}
export default Loading
