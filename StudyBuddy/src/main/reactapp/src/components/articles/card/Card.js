import './Card.css';
import PropTypes from "prop-types";
import React, {useEffect} from "react";
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';


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
    },[])

    return(
        <div className="card">
            <h2>{props.title}</h2>
            <div dangerouslySetInnerHTML={{__html:props.text}}/>
            <div>
                <input value={comment} type="text" placeholder="Add new comment" onChange={handleCommentChange} />
                <button onClick={handelOnClick}>Post comment</button>
            </div>
            {isMatch ? (<><button onClick={openComments}>Comments<KeyboardArrowUpIcon/></button></>) : (<><button onClick={openComments}>Comments<KeyboardArrowDownIcon/></button> <br/> {comments.map(comm => (<p>{comm.text}</p>))}</>)}
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