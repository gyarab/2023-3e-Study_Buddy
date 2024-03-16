import React from 'react';
import {Navigate} from "react-router-dom";

function ResetPassword (){
    const [oldPassword, setOldPassword] = React.useState('');
    const [newPassword, setNewPassword] = React.useState('');
    const [goToChange, setGoToChange] = React.useState(false);

    const handelOnClick=(e)=>{
        e.preventDefault()
        const passwordChange={oldPassword, newPassword}
        fetch("http://localhost:8080/api/v1/student/password",{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(passwordChange)
        })
        setGoToChange(true);
    }
    const handleOldPasswordChange = (event) => {
        setOldPassword(event.target.value);
    };

    const handleNewPasswordChange = (event) => {
        setNewPassword(event.target.value);
    };

    if(goToChange){
        return <Navigate to={"/user/account"}/>;
    }

    return (
        <div className="auth-form-container">
            <h1>Change your password</h1>
            <input value={oldPassword} type="password" placeholder="Old password" onChange={handleOldPasswordChange} />
            <input value={newPassword} type="password" placeholder="New Password" onChange={handleNewPasswordChange} />
            <button onClick={handelOnClick}>Reset Password</button>
        </div>
    );
}

export default ResetPassword;