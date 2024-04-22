import React from "react";

function Home(){

    return(
        <div className={"body-sec"}>
            <div className={"head-sec"}>
                <h1 className={"headline-log-reg-text"}>
                    Welcome to StudyBuddy!
                </h1>

                <div className={"underline"}></div>
            </div>
            <div className={"head-sec-sm"}>
                <p className={"none-articles"}>
                    StudyBuddy is where students like you meet! Share your notes, study materials and tips with other students and find inspiration for your academic success.
                </p>
                <p className={"none-articles"}>
                    What you can expect on StudyBuddy:
                </p>

                <ul className={"ul-style"}>
                    <li>
                        <div className={"none-articles"}>Share your notes and study materials with others.</div>
                    </li>
                    <li>
                        <div className={"none-articles"}>View other students notes and materials.</div>
                    </li>
                    <li>
                        <div className={"none-articles"}>Make new friendships and collaborations with students with similar interests.</div>
                    </li>
                </ul>

                <p className={"none-articles"}>
                    Join our community and let's support each other in the studio together!
                </p>

                <div className={"button-homepage"}>
                    <button><a className="nav-link" href="/user/articles/">See all articles</a></button>
                </div>
            </div>
        </div>);
}

export default Home