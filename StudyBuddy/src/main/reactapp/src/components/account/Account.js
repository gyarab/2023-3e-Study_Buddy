import React, {useEffect, useState} from "react";
import AccountCard from "./AccountCard/AccountCard";
import PersonIcon from '@mui/icons-material/Person';


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
    const listArticles = articles.map(article => <div className={"article"}><AccountCard title={article.title} text={article.article} id={article.id}/></div>)

    return(
        <>
            <div className={"body-sec"}>
                <div className={"head-sec"}>
                    <h2 className={"headline-log-reg-text"}>Account</h2>
                    <div className={"underline"}></div>
                </div>
                <div className={"password-buttons"}>
                    <button><a className="nav-link" href="/login/reset_password">Reset your password</a></button>
                </div>
                <div className={"head-sec-sm"}>
                    <h3 className={"headline-sec"}>Reset your username</h3>
                    <div className={"underline-sm"}></div>
                </div>
                <div className={"inputs"}>
                    <div className={"username-reset"}>

                        <div className={"input-sec"}>
                            <PersonIcon className={"input-icon-sec"}/><input value={username} type="text" placeholder="New Username" onChange={handleUsernameChange} />
                        </div>
                        <button onClick={handelOnClickChangeUsername}>Reset</button>
                    </div>
                </div>
                <div className={"head-sec-sm"}>
                    <h3 className={"headline-sec"}>Your articles</h3>
                    <div className={"underline-sm"}></div>
                </div>
                <div className={"articles"}>
                    {listArticles.length==0 ? (<p className={"none-articles"}>None articles posted</p>):(<>{listArticles}</>)}
                </div>
            </div>
        </>);
}

export default Account