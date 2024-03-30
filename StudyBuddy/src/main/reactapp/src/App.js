import './App.css';
import './components/css/RegisterLogin.css';

import {BrowserRouter, Route, Routes} from 'react-router-dom';
import CreateArticle from "./components/create_article/CreateArticle";
import Articles from "./components/articles/Articles"
import Home from "./components/home/Home"
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Account from "./components/account/Account";
import NavBar from "./components/navbar/NavBar";
import ResetPassword from "./components/reset_password/ResetPassword";
import UpdateArticle from "./components/update_article/UpdateArticle";
import {useMediaQuery, useTheme} from "@mui/material";

function App() {
  return (
      <div className="App">
          <NavBar/>
          <div className="content container">
              <BrowserRouter>
                  <Routes>
                      <Route index element={<Home />} />
                      <Route path="/login/" element={<Login/>} />
                      <Route path="/login/reset_password/" element={<ResetPassword />} />
                      <Route path="/register/" element={<Register />} />
                      <Route path="/user/account/" element={<Account />} />
                      <Route path="/user/article/create/" element={<CreateArticle />} />
                      <Route path="/user/article/update/" element={<UpdateArticle />} />
                      <Route path="/user/articles/" element={<Articles />} />
                  </Routes>
              </BrowserRouter>
        </div>
      </div>
  );
}

export default App;
