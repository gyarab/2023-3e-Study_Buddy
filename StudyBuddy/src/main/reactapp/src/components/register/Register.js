import React, {useEffect} from 'react';
import {Navigate} from "react-router-dom";
import PersonIcon from '@mui/icons-material/Person';
import EmailIcon from '@mui/icons-material/Email';
import EnhancedEncryptionIcon from "@mui/icons-material/EnhancedEncryption";

function Register (){
    const [name, setUsername] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [goToChange, setGoToChange] = React.useState(false);


    useEffect(()=>{
        setEmail(JSON.parse(window.localStorage.getItem('EMAIL')))
        setUsername(JSON.parse(window.localStorage.getItem('USERNAME')))
    },[])


    useEffect(()=>{
        window.localStorage.setItem('EMAIL', JSON.stringify(email))
        window.localStorage.setItem('USERNAME', JSON.stringify(name))
    },[email, name])



    const handelOnClick=(e)=>{
        e.preventDefault()
        const student={name,email,password}
        fetch("http://localhost:8080/api/v1/registration",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(student)
        })
        setGoToChange(true);
    }
    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value.toLowerCase());
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    if(goToChange){
        return <Navigate to={"/login"}/>;
    }

    const handleLoginPage = (event) => {
        setGoToChange(true);
    }

    return (
        <div className="auth-form-container body-log-reg">
            <div className={"headline-log-reg"}>
                <h2 className={"headline-log-reg-text"}>Sign-up</h2>
                <div className={"underline"}></div>
            </div>
            <div className={"inputs"}>
                <div className={"input"}>
                    <PersonIcon className={"input-icon"}/><input value={name} type="text" placeholder="Username" onChange={handleUsernameChange} /><br/>
                </div>
                <div className={"input"}>
                    <EmailIcon className={"input-icon"}/><input value={email} type="email" placeholder="Email" onChange={handleEmailChange} /><br/>
                </div>
                <div className={"input"}>
                    <EnhancedEncryptionIcon className={"input-icon"}/><input value={password} type="password" placeholder="Password" onChange={handlePasswordChange} />
                </div>
            </div>
            <div className={"buttons"}>
                <div className={"button"}>
                    <button onClick={handleLoginPage}>Log-in</button>
                </div>
                <div className={"button while-button"}>
                    <button onClick={handelOnClick}>Sign-up</button>
                </div>
            </div>
        </div>
    );
}

export default Register;