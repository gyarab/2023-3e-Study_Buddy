import React, {useEffect, useState} from "react";
import AccountCard from "./AccountCard/AccountCard";

function Account(){

    const [articles, setArticles]=useState([]);
    const [username, setUsername]=useState('');

    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/article/autor")
            .then(res=>res.json())
            .then((result)=>{
                setArticles(result);
            })
    },[])

    const handelOnClickChangeUsername=(e)=>{
        e.preventDefault()
        fetch("http://localhost:8080/api/v1/student/username",{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(username)
        })
    };
    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };
    const listArticles = articles.map(article => <><AccountCard title={article.title} text={article.article} id={article.id}/><br/></>)

    return(
        <>
            <div>
                <div>
                    <button><a className="nav-link" href="/login/reset_password">Reset your password</a></button>
                </div>
                <div>
                    <p>Reset your username</p>
                    <input value={username} type="text" placeholder="new username" onChange={handleUsernameChange} />
                    <button onClick={handelOnClickChangeUsername}>Post comment</button>
                </div>
                <div>
                    <p>Your articles:</p>
                    {listArticles}
                </div>
            </div>
        </>);
}

export default Account