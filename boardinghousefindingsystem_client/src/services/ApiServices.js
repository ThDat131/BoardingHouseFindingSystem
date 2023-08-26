import axios from "axios";
import cookie from "react-cookies"

const SERVER_CONTEXT = "/BoardingHouseFindingSystem";
const baseURL = "http://localhost:8080";

const loadAllProvinces = async () => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/provinces/`)
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
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/province/${code}/districts/`)
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

        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/district/${code}/wards/`)
        if (res.status === 200) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
}

const loadAllRoom = async () => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/rooms/`, {
            headers: {
                "Authorization": cookie.load("token")
            }
        })
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

const addPostRentalRoom = async(data) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/post`, data, {
            headers: {
                'Content-Type': `multipart/form-data; boundary=${data._boundary}`,
            }
        })
        if (res.status === 200) {
            return res
        }
    } catch(ex){
        console.log(ex)
    }
}

const getAllPost = async() => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/posts`)
        if (res.status === 200) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
}

const login = async(username, password) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/login/`, {
            username: username,
            password: password
        })
        return res
    } catch(ex) {
        // console.log(ex)
    }
}

const getCurrentUser = async () => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/current-user/`, {
            headers: {
                "Authorization": cookie.load("token")
            }
        })
        if (res.status === 200) 
            return res
    } catch(ex) {
        console.error(ex)
    }
}

const signUpLandLord = async (data) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/landlord-user/`, data, {
            headers: {
                'Content-Type': `multipart/form-data; boundary=${data._boundary}`,
            } 
        })
        return res;
    } catch(err) {
        return err
    }
}

const signUpTentant = async (data) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/tentant-user/`, data, {
            headers: {
                'Content-Type': `multipart/form-data; boundary=${data._boundary}`,
            }
        })
        return res;
    } catch (err) {
        return err
    }
}

export { 
    loadAllProvinces, 
    loadAllDistrictsByProvinceCode,
    loadAllWardssByDistrictCode,
    loadAllRoom, 
    addRoom, 
    deleteRoom, 
    getRoomById, 
    updateRoom, 
    addPostRentalRoom,
    getAllPost,
    login,
    getCurrentUser,
    signUpLandLord,
    signUpTentant
}