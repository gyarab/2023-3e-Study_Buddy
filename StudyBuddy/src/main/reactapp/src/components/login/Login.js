import React, {useEffect, useState} from 'react';

function Login () {
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    return (
        <form name="f"  method="post">
            <fieldset>
                <h1>Log-in</h1>
                <input value={email} type="text" id="username" placeholder="Email" onChange={handleEmailChange} name="username" />
                <input value={password} type="password" id="password" placeholder="Password" onChange={handlePasswordChange} name="password"/>
                <div className="form-actions">
                    <button>Log-in</button>
                </div>
                <p className="link-btn">Don't have an account? <a className="nav-link" href="/register"> Register</a></p>
            </fieldset>
        </form>
    );
}

export default Login;