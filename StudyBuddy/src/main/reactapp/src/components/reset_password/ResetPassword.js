import React from 'react';
import {Navigate} from "react-router-dom";
import EditIcon from '@mui/icons-material/Edit';
import EnhancedEncryptionIcon from "@mui/icons-material/EnhancedEncryption";


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
        <div className="auth-form-container body-log-reg">
            <div className={"headline-log-reg"}>
                <h4  className={"headline-log-reg-text"}>Change your password</h4>
                <div className={"underline"}></div>
            </div>
            <div className={"inputs"}>
                <div className={"input"}>
                    <EnhancedEncryptionIcon className={"input-icon"}/><input value={oldPassword} type="password" placeholder="Old password" onChange={handleOldPasswordChange} />
                </div>
                <div className={"input"}>
                    <EditIcon className={"input-icon"}/><input value={newPassword} type="password" placeholder="New password" onChange={handleNewPasswordChange} />
                </div>
            </div>

            <div className={"buttons"}>
                <div className={"button"}>
                    <button onClick={handelOnClick}>Reset</button>
                </div>
            </div>
        </div>
    );
}

export default ResetPassword;