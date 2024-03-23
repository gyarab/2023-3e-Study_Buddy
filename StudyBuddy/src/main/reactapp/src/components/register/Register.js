import React from 'react';
import {Navigate} from "react-router-dom";

function Register (){
    const [name, setUsername] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [goToChange, setGoToChange] = React.useState(false);

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


    return (
        <div className="auth-form-container">
            <h1>Sign-up</h1>
            <input value={name} type="text" placeholder="Username" onChange={handleUsernameChange} />
            <input value={email} type="email" placeholder="Email" onChange={handleEmailChange} />
            <input value={password} type="password" placeholder="Password" onChange={handlePasswordChange} />
            <button onClick={handelOnClick}>Sign-up</button>
            <p className="link-btn">Already have an account? <a className="nav-link" href="/login"> Log in</a> </p>
        </div>
    );
}

export default Register;