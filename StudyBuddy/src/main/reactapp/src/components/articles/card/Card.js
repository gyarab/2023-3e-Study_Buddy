import './Card.css';
import PropTypes from "prop-types";

function Card(props){
    return(
        <div className="card">
            <h2>{props.title}</h2>
            <p>{props.text}</p>
        </div>
    )
}

Card.propTypes = {
    title: PropTypes.string,
    text: PropTypes.string,
}

Card.defaultProps = {
    title: "title of the article",
    text: "text that describes the article",
}


export default Card