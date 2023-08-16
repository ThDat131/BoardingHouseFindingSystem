import { BrowserRouter, Route, Routes } from "react-router-dom"
import Header from "./components/Header"
import Main from "./pages/Main/Main"
import ManageRoom from "./pages/ManageRoom/ManageRoom"
import EditRoom from "./pages/EditRoom/EditRoom"


const App = () => {
  return <>
    <Header />
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main/>}/>
      </Routes>
      <Routes>
        <Route path="/quan-ly-nha-tro" element={<ManageRoom />} />
      </Routes>
      <Routes>
        <Route path="/quan-ly-nha-tro/room/:id" element={<EditRoom />} />
      </Routes>
    </BrowserRouter>
  </>
}
export default App