import '../../articles/card/Card.css';
import PropTypes from "prop-types";
import React, {useState} from "react";
import {Navigate} from "react-router-dom";

function AccountCard(props){

    const [nextPage, setNextPage] = useState(false);

    const handelOnClickDelate = (event) => {
        const articleId = props.id;
        event.preventDefault()
        fetch("http://localhost:8080/api/v1/article/"+articleId,{
            method:"DELETE"
        })
    };

    const handelOnClickUpdate = (event) => {

        window.localStorage.setItem('SET_TITLE', JSON.stringify(props.title));
        window.localStorage.setItem('SET_ARTICLE', JSON.stringify(props.text));
        setNextPage(true);
    };

    if(nextPage){
        return  <Navigate to={"/user/article/update"}/>;
    }

    return(
        <div className="card">
            <h2>{props.title}</h2>
            <div dangerouslySetInnerHTML={{__html:props.text}}/>
            <div>
                <button onClick={handelOnClickDelate}>Delete Article</button>
                <button onClick={handelOnClickUpdate}>Update Article</button>
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