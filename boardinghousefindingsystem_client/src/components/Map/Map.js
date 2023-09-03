import { GoogleMap, useJsApiLoader, useLoadScript } from "@react-google-maps/api"
import Loading from "../Loading/Loading"
import { useRef } from "react"
import { useCallback } from "react"
import { useState } from "react"
import { memo } from "react"

const Map = ({address}) => {

    const initPos = { lat: 10.7552929, lng: 106.3655584 }
    const { isLoaded } = useJsApiLoader({
        googleMapsApiKey: "AIzaSyBRGVdXzYMGtFyX7RXVoMz7RT1CjVuuoX4"
    }) 
    
    const mapRef = useRef()
    const onLoad = useCallback((map) => {
        mapRef.current = map
    }, [])

    if(isLoaded)
        return <Loading />
    
    return <GoogleMap mapContainerStyle={{width: '100%', height: '300px'}}
            zoom={10}
            center={initPos}
            mapContainerClassName="map-container"
            onLoad={onLoad}>
        </GoogleMap>
    
}

export default Map