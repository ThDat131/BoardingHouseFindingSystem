import axios from "axios";

const SERVER_CONTEXT = "/BoardingHouseFindingSystem";
const baseURL = "http://localhost:8080";

const loadAllProvinces = async () => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/provinces`)
        if (res.status === 200) {
            return res
        } 
    }
    catch(ex) {
        console.log(ex)
    }
    
}

const loadAllDistrictsByProvinceCode = async(code) => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/province/${code}/districts`)
        if (res.status === 200) {
            return res
        }
    }
    catch (ex) {
        console.log(ex)
    }
    
}

const loadAllWardssByDistrictCode = async (code) => {
    try {

        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/district/${code}/wards`)
        if (res.status === 200) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
}

const loadAllRoomByUsername = async (username) => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/${username}/rooms`)
        if (res.status === 200) {
            return res
        }

    } catch (ex) {
        console.log(ex)
    }
}

const addRoom = async (bodyParams) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/room`, bodyParams)
        if (res.status === 201) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
    
}

const deleteRoom = async (id) => {
    try {
        const res = await axios.delete(`${baseURL}${SERVER_CONTEXT}/api/room/${id}`)
        if (res.status === 204) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
}

const updateRoom = async (bodyParams) => {
    try {
        const res = await axios.put(`${baseURL}${SERVER_CONTEXT}/api/room`, bodyParams)
        if (res.status === 200) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
}

const getRoomById = async (id) => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/room/${id}`)
        if (res.status === 200) {
            return res
        }
    } catch(ex) {
        console.log(ex)
    }
}

export { loadAllProvinces, loadAllDistrictsByProvinceCode, loadAllWardssByDistrictCode, loadAllRoomByUsername, addRoom, deleteRoom, getRoomById, updateRoom }