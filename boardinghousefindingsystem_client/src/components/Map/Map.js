import { GoogleMap, useJsApiLoader, useLoadScript } from "@react-google-maps/api"
import Loading from "../Loading/Loading"
import { useRef } from "react"
import { useCallback } from "react"
import { useState } from "react"
import { memo } from "react"
import MapGL, { Marker } from '@goongmaps/goong-map-react'

const Map = ({ lat, lng }) => {

    const [viewport, setViewport] = useState({
        latitude: lat,
        longitude: lng,
        zoom: 16
    });
    
    return <MapGL
        {...viewport}
        width="100%"
        height="400px"
        onViewportChange={(viewport) => setViewport(viewport)
        }
        mapStyle="https://tiles.goong.io/assets/goong_map_dark.json"
        goongApiAccessToken="0t9tq8g3b8q6KXA3oR4vKZdI9VIuXEioA3dhZPTC"
    >
        <Marker
            latitude={lat}
            longitude={lng}
            >
            <div><i className="fa-solid fa-location-dot" style={{color:"#fff"}}></i></div>

        </Marker>
    </MapGL>
    
}

export default Map