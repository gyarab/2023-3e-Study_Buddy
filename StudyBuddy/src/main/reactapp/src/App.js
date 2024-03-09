import './App.css';

import {BrowserRouter, Route, Routes} from 'react-router-dom';
import CreateArticle from "./components/create/article/CreateArticle";
import CreateComment from "./components/create/comment/CreateComment";
import Articles from "./components/articles/Articles"
import Home from "./components/home/Home"
import Login from "./components/login/Login"
import Register from "./components/register/Register"
import Account from "./components/account/Account";
import NavBar from "./components/navbar/NavBar";
import {useState} from "react";

function App() {
  return (
      <div className="App">
        <NavBar/>
        <div className="content">
          <BrowserRouter>
            <Routes>
              <Route index element={<Home />} />
              <Route path="/login/" element={<Login/>} />
              <Route path="/register/" element={<Register />} />
              <Route path="/user/acount/**/" element={<Account />} />
              <Route path="/user/article/create/" element={<CreateArticle />} />
              <Route path="/user/comment/create/" element={<CreateComment/>} />
              <Route path="/user/articles/" element={<Articles />} />
            </Routes>
          </BrowserRouter>
        </div>
      </div>
  );
}

export default App;