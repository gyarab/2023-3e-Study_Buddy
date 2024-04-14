import React, {useEffect, useState} from 'react';
import EmailIcon from "@mui/icons-material/Email";
import EnhancedEncryptionIcon from '@mui/icons-material/EnhancedEncryption';
import {Navigate} from "react-router-dom";

function Login () {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [goToChange, setGoToChange] = React.useState(false);//body-log-reg-md

    const handleEmailChange = (event) => {
        setEmail(event.target.value.toLowerCase());
    };

    useEffect(()=>{
        setEmail(JSON.parse(window.localStorage.getItem('EMAIL')))
    },[])

    useEffect(()=>{
        window.localStorage.setItem('EMAIL', JSON.stringify(email))
    },[email])

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };


    if(goToChange){
        return <Navigate to={"/register"}/>;
    }

    const handleLoginPage = (event) => {
        setGoToChange(true);
    }

    return (
        <form name="f" className={"body-log-reg"} method="post">
            <fieldset>
                <div className={"headline-log-reg"}>
                    <h2 className={"headline-log-reg-text"}>Log-in</h2>
                    <div className={"underline"}></div>
                </div>
                <div className={"inputs"}>
                    <div className={"input"}>
                        <EmailIcon className={"input-icon"}/><input value={email} type="text" id="username" placeholder="Email" onChange={handleEmailChange} name="username" /><br/>
                    </div>
                    <div className={"input"}>
                        <EnhancedEncryptionIcon className={"input-icon"}/><input value={password} type="password" id="password" placeholder="Password" onChange={handlePasswordChange} name="password"/>
                    </div>
                </div>
                <div className={"buttons"}>
                    <div className="form-actions button while-button">
                        <button >Log-in</button>
                    </div>
                    <div className={"button"}>
                        <button onClick={handleLoginPage}>Sign-up</button>
                    </div>
                </div>
            </fieldset>
        </form>
    );
}

export default Login;