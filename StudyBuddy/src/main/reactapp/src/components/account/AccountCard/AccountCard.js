import '../../css/Card.css';
import PropTypes from "prop-types";
import React, {useState} from "react";
import {Navigate} from "react-router-dom";

function AccountCard(props){

    const [nextPage, setNextPage] = useState(false);
    const [homepage, setHomepage] = useState(false);

    const handelOnClickDelate = (event) => {
        const articleId = props.id;
        event.preventDefault()
        fetch("http://localhost:8080/api/v1/article/"+articleId,{
            method:"DELETE"
        })
        setHomepage(true);
    };

    const handelOnClickUpdate = (event) => {

        window.localStorage.setItem('SET_TITLE', JSON.stringify(props.title));
        window.localStorage.setItem('SET_ARTICLE', JSON.stringify(props.text));
        setNextPage(true);
    };

    if(nextPage){
        return  <Navigate to={"/user/article/update"}/>;
    }

    if(homepage){
        return  <Navigate to={"/"}/>;
    }

    return(
        <div className="card">
            <h2 className={"article-title"} >{props.title}</h2>
            <div className={"underline-sm"}></div>
            <div className={"article-text"} dangerouslySetInnerHTML={{__html:props.text}}/>
            <div className={"article-buttons"}>
                <div className={"article-button-delete"}>
                    <button onClick={handelOnClickDelate}>Delete Article</button>
                </div>
                <div>
                    <button onClick={handelOnClickUpdate}>Update Article</button>
                </div>
            </div>
        </div>
    )
}

AccountCard.propTypes = {
    title: PropTypes.string,
    text: PropTypes.string,
    id: PropTypes.object,
}

AccountCard.defaultProps = {
    title: "title of the article",
    text: "text that describes the article",
    id: null,
}


export default AccountCard