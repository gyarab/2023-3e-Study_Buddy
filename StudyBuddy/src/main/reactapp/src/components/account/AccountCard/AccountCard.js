import '../../articles/card/Card.css';
import PropTypes from "prop-types";
import React from "react";

function AccountCard(props){


    const handelOnClick = (event) => {
        const articleId = props.id;
        event.preventDefault()
        fetch("http://localhost:8080/api/v1/article/"+articleId,{
            method:"DELETE"
        })
    };


    return(
        <div className="card">
            <h2>{props.title}</h2>
            <div dangerouslySetInnerHTML={{__html:props.text}}/>
            <div>
                <button onClick={handelOnClick}>Delete Article</button>
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