import '../../css/Card.css';
import PropTypes from "prop-types";
import React, {useEffect} from "react";
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import CommentIcon from '@mui/icons-material/Comment';



function Card(props){

    const [comment, setComment] = React.useState('');
    const [comments, setComments] = React.useState([]);
    const [isMatch, setIsMatch] = React.useState(true);

    const handleCommentChange = (event) => {
        setComment(event.target.value);
    };

    const openComments = (event) => {
        setIsMatch(!isMatch);
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

    useEffect(()=>{
        const articleId = props.id
        fetch("http://localhost:8080/api/v1/comment/"+articleId,{})
            .then(res=>res.json())
            .then((result)=>{
                setComments(result);
            })
    },[isMatch])

    return(
        <div className="card">
            <h2 className={"article-title"}>{props.title}</h2>
            <div className={"underline-sm"}></div>
            <div className={"article-text"} dangerouslySetInnerHTML={{__html:props.text}}/>

            <div className={"username-reset-card"}>
                <div className={"input-third"}>
                    <CommentIcon className={"input-icon-sec"}/><input value={comment} type="text" placeholder="Add new comment" onChange={handleCommentChange} />
                </div>
                <button onClick={handelOnClick}>Post</button>
            </div>
                {isMatch ? (
                    <div className={"comments"}>
                        <button onClick={openComments}>Comments<KeyboardArrowDownIcon/></button>
                    </div>
                ) : (
                    <div className={"comments"}>
                        <button onClick={openComments}>Comments<KeyboardArrowUpIcon/></button>
                            {comments.length==0 ? (
                                <div className={"none-comments"}>None comments found</div>
                            ) : (
                                <div className={"comment-other-line"}>{comments.map(comm => (<div><div className={"comment"}>{comm.text}</div></div>))}</div>
                            )}
                    </div>
                )}
        </div>
    )
}

Card.propTypes = {
    title: PropTypes.string,
    text: PropTypes.string,
    id: PropTypes.object,
}

Card.defaultProps = {
    title: "title of the article",
    text: "text that describes the article",
    id: null,
}


export default Card