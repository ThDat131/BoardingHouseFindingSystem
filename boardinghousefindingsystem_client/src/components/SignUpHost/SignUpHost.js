import "./sign-up-host.css";
import React, { useRef, useState } from "react";

const SignUpHost = ({isHost, addressData, filesData}) => {

    const filesRef = useRef([])
    const [address, setAddress] = useState("")

    const handleChangeFiles = (evt) => {
        
        if (evt.target.files) {
            // setImageUrls([])
            filesRef.current = []
            for (let file of evt.target.files) {
                filesRef.current = [...filesRef.current, file]
                // setImageUrls(prevImages => [...prevImages, URL.createObjectURL(file)])
            }
        }
        filesData(filesRef)
    }

    const handleChangeAddress = (evt) => {
        const address = evt.target.value
        setAddress(address)
        addressData(address)
    }
    

    return (
        <>
            <input value={address} type="text" name="address" id="address" placeholder="Địa chỉ" className="p-2 mt-2" onChange={handleChangeAddress}/>
            <div className="mt-2">
                <label>Hình ảnh trọ (tối thiểu 3 hình)</label>
                <input type="file" multiple onChange={handleChangeFiles} className="p-2 mt-2 w-100" />
            </div>
            
        </>
    );
}

export default SignUpHost;