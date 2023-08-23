import { useLottie } from "lottie-react";
import animationLoading from "../../animation/animation_loading.json";


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
        <div class="text-center m-3">
            <div class="spinner-border" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
    </>
}
export default Loading
