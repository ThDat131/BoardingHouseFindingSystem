import { BrowserRouter, Route, Routes } from "react-router-dom"
import Main from "./pages/Main/Main"
import ManageRoom from "./pages/ManageRoom/ManageRoom"
import EditRoom from "./pages/EditRoom/EditRoom"
import Header from "./components/Header/Header"
import RoomRental from "./pages/RoomRental/RoomRental"
import AddPostRental from "./pages/AddPostRental/AddPostRental"
import SigninSignup from "./pages/SigninSignup/SigninSignup"
import Signin from "./pages/Signin/Signin"
import Signup from "./pages/Signup/Signup"
import ChangePassForm from "./components/ChangePassForm/ChangePassForm"
import cookie from "react-cookies"
import { createContext, useReducer } from "react"
import MyUserReducer from "./reducers/MyUserReducer"
import { ToastContainer } from "react-toastify"
import Personal from "./components/Personal/Personal"

export const MyUserContext = createContext();

const App = () => {

  const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);

  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/quan-ly-nha-tro" element={<ManageRoom />} />
          <Route path="/quan-ly-nha-tro/room/:id" element={<EditRoom />} />
          <Route path="/tin-tim-nha-tro" element={<RoomRental />} />
          <Route path="/dang-tin-cho-thue" element={<AddPostRental />} />
          <Route path="/dang-ky-dang-nhap" element={<SigninSignup />} />
          <Route path="/dang-nhap" element={<Signin />} />
          <Route path="/dang-ky" element={<Signup />} />
          <Route path="/doi-mat-khau" element={<ChangePassForm />} />
          <Route path="/trang-ca-nhan" element={<Personal />} />

        </Routes>
        <ToastContainer />
      </BrowserRouter>
    </MyUserContext.Provider>
  )
    
  
}
export default App