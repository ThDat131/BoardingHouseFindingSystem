import { useLottie } from "lottie-react";
import animationLoading from "../../animation/animation_loading.json";


const Loading = () => {
    const options = {
        animationData: animationLoading,
        loop: true
    };
    

    const { View } = useLottie(options);
    return <>
        <div style={{width: '100px', height: '100px'}}>
            {View}
        </div>
        
    </>
}
export default Loading
