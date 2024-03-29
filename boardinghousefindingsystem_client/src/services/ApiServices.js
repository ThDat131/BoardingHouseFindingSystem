import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/BoardingHouseFindingSystem";
const baseURL = "http://localhost:8080";
const key = "E6VybWF4vHC9mPtpXbh3u09KYcrtRyplcypeJLTV"

const loadAllProvinces = async () => {
  try {
    const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/provinces/`);
    if (res.status === 200) {
      return res;
    }
  } catch (ex) {
    console.log(ex);
  }
};

const loadAllDistrictsByProvinceCode = async (code) => {
  try {
    const res = await axios.get(
      `${baseURL}${SERVER_CONTEXT}/api/province/${code}/districts/`
    );
    if (res.status === 200) {
      return res;
    }
  } catch (ex) {
    console.log(ex);
  }
};

const loadAllWardssByDistrictCode = async (code) => {
  try {
    const res = await axios.get(
      `${baseURL}${SERVER_CONTEXT}/api/district/${code}/wards/`
    );
    if (res.status === 200) {
      return res;
    }
  } catch (ex) {
    console.log(ex);
  }
};

const loadAllRoom = async () => {
  try {
    const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/rooms/`, {
      headers: {
        Authorization: cookie.load("token"),
      },
    });
    if (res.status === 200) {
      return res;
    }
  } catch (ex) {
    console.log(ex);
  }
};

const addRoom = async (bodyParams) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/room/`, bodyParams, {
            headers: {
                "Authorization": cookie.load("token")
            }
        })
        if (res.status === 201) {
            return res
        }
    } catch (ex) {
        return ex
    }
};

const deleteRoom = async (id) => {
    try {
        const res = await axios.delete(`${baseURL}${SERVER_CONTEXT}/api/room/${id}/`, {
            headers: {
                Authorization: cookie.load("token")
            }
        })
            return res
    } catch (ex) {
        console.log(ex)
    }
};

const updateRoom = async (bodyParams) => {
    try {
        const res = await axios.put(`${baseURL}${SERVER_CONTEXT}/api/room/`, bodyParams, {
            headers: {
                Authorization: cookie.load("token")
            }
        })
        if (res.status === 200) {
            return res
        }
    } catch (ex) {
        console.log(ex)
    }
};

const getRoomById = async (id) => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/room/${id}/`, {
            headers: {
                Authorization: cookie.load("token")
            }
        })
        return res
        
    } catch(ex) {
        console.log(ex)
    }
};

const addPostRentalRoom = async(data) => {
    try {
        const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/post/`, data, {
            headers: {
                'Content-Type': `multipart/form-data; boundary=${data._boundary}`,
                'Authorization': cookie.load("token")
            }
        })
        return res
        
    } catch(ex){
        return ex
    }
};

const getAllPost = async (requestparms) => {
  try {
    if (requestparms === undefined) {
      requestparms = ""
    }
    const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/posts/?${requestparms}`);
    if (res.status === 200) {
      return res;
    }
  } catch (ex) {
    console.log(ex);
  }
};

const login = async (username, password) => {
  try {
    const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/login/`, {
      username: username,
      password: password,
    });
    return res;
  } catch (ex) {
    // console.log(ex)
  }
};

const getCurrentUser = async () => {
  try {
    const res = await axios.get(
      `${baseURL}${SERVER_CONTEXT}/api/current-user/`,
      {
        headers: {
          Authorization: cookie.load("token"),
        },
      }
    );
    if (res.status === 200) return res;
  } catch (ex) {
    console.error(ex);
  }
};

const signUpLandLord = async (data) => {
  try {
    const res = await axios.post(
      `${baseURL}${SERVER_CONTEXT}/api/landlord-user/`,
      data,
      {
        headers: {
          "Content-Type": `multipart/form-data; boundary=${data._boundary}`,
        },
      }
    );
    return res;
  } catch (err) {
    return err;
  }
};

const signUpTentant = async (data) => {
  try {
    const res = await axios.post(
      `${baseURL}${SERVER_CONTEXT}/api/tentant-user/`,
      data,
      {
        headers: {
          "Content-Type": `multipart/form-data; boundary=${data._boundary}`,
        },
      }
    );
    return res;
  } catch (err) {
    return err;
  }
};

const getTentantDetails = async () => {
  try {
    const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/tentant/`, {
      headers: {
        Authorization: cookie.load("token"),
      },
    });
    return res;
  } catch (err) {
    return err;
  }
};

const changeTentantDetails = async (data) => {
    try {
        const res = await axios.put(`${baseURL}${SERVER_CONTEXT}/api/tentant-details/`, data, {
          headers: {
            Authorization: cookie.load("token"),
          },
        });

        if (res === 204) {
            return res;
        }
      } catch (err) {
        console.log(err)
      }
}

const postRentalDetail = async (id) => {
    try {
        const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/post/${id}/`)
        return res
    } catch (err) {
        return err
    }
}

const getLatLngAddress = async(address) => {
    try {
      const res = await axios.get(`https://rsapi.goong.io/geocode?address=${address}&api_key=${key}`)
        return res
    } catch (err) {
        return err
    }
}

const addComment = async(data) => {
  try {
    const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/comment/`, data, {
      headers: {
        Authorization: cookie.load("token")
      }
    })
    return res
  }
  catch(err) {
    return err
  }
}
const addFollow = async (landlordId) => {
  try {
    const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/follow/${landlordId}/`,{}, {
      headers: {
        Authorization: cookie.load("token")
      }
    })
    return res
  }
  catch (err) {
    return err
  }
}
const deleteFollow = async (landlordId) => {
  try {
    const res = await axios.delete(`${baseURL}${SERVER_CONTEXT}/api/follow/${landlordId}/`, {
      headers: {
        Authorization: cookie.load("token")
      }
    })
    return res
  }
  catch (err) {
    return err
  }
}

const getPostsTentant = async () => {
  try {
    const res = await axios.get(`${baseURL}${SERVER_CONTEXT}/api/posts-tentant/`)
    return res
  } catch (err) {
    return err
  }
}

const addPostTental = async(data) => {
  try {
    const res = await axios.post(`${baseURL}${SERVER_CONTEXT}/api/post-tentant/`, data, {
      headers: {
        Authorization: cookie.load("token")
      }
    })
    return res
  } catch(err) {
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
  signUpTentant,
  postRentalDetail,
  getLatLngAddress,
  getTentantDetails,
  changeTentantDetails,
  addComment,
  addFollow,
  deleteFollow,
  getPostsTentant,
  addPostTental,
}
