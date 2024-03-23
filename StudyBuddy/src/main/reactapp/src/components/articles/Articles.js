import Card from "./card/Card";
import './Articles.css';
import {useEffect, useState} from "react";

function Articles(){

    const [articles, setArticles]=useState([]);


    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/article")
            .then(res=>res.json())
            .then((result)=>{
                setArticles(result);
            })
    },[])

    const listArticles = articles.map(article => <><Card title={article.title} text={article.article} id={article.id}/><br/></>)

    return(
        <>
            {listArticles}
        </>)
}

export default Articles