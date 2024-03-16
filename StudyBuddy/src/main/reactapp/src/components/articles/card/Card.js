import './Card.css';
import PropTypes from "prop-types";
import React from "react";

function Card(props){

    const [comment, setComment] = React.useState('');

    const handleCommentChange = (event) => {
        setComment(event.target.value);
    };

    const handelOnClick=(e)=>{
        e.preventDefault()
        const articleId = props.id
        const newComment={comment, articleId}
        fetch("http://localhost:8080/api/v1/comment",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(newComment)
        })
    }

    return(
        <div className="card">
            <h2>{props.title}</h2>
            <div dangerouslySetInnerHTML={{__html:props.text}}/>
            <div>
                <input value={comment} type="text" placeholder="Add new comment" onChange={handleCommentChange} />
                <button onClick={handelOnClick}>Post comment</button>
            </div>
        </div>
    )
}

Card.propTypes = {
    title: PropTypes.string,
    text: PropTypes.string,
    id: PropTypes.any,
}

Card.defaultProps = {
    title: "title of the article",
    text: "text that describes the article",
    id: null,
}


export default Card