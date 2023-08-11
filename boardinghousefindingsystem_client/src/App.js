import { BrowserRouter, Route, Routes } from "react-router-dom"
import Header from "./components/Header"
import Main from "./pages/Main/Main"


const App = () => {
  return <>
    <Header />
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main/>}/>
      </Routes>
    </BrowserRouter>
    <h1>Hello World</h1>
  </>
}
export default App