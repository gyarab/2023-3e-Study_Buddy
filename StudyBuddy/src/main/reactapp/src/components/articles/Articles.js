import Card from "./card/Card";
import './Articles.css';
import React, {useEffect, useState} from "react";
import {Select} from "@mui/material";

function Articles(){

    const [articles, setArticles]=useState([]);
    const [subjects, setSubjects]=useState([]);
    const [path, setPath] = useState("http://localhost:8080/api/v1/article");
    const [articleSubject, setArticleSubject] = useState(0);


    useEffect(()=>{
        fetch(path)
            .then(res=>res.json())
            .then((result)=>{
                setArticles(result);
            })
        fetch("http://localhost:8080/api/v1/subjects")
            .then(res=>res.json())
            .then((result)=>{
                setSubjects(result);
            })
    },[])

    const handleSubjectChange = (event) => {
        setArticleSubject(event.target.value);
        if(articleSubject!=0){
            setPath("http://localhost:8080/api/v1/article/" + articleSubject)
        } else {
            setPath("http://localhost:8080/api/v1/article")
        }
    };

    const listArticles = articles.map(article => <><Card title={article.title} text={article.article} id={article.id}/><br/></>)
    const listSubjects = subjects.map(subject => <option value={subject.id}>{subject.name}</option>)

    return(
        <>
            <select className={"selectSubject"} onChange={handleSubjectChange}>
                <option value={0}>All subjects</option>
                {listSubjects}
            </select>
            <button onClick={handleSubjectChange}>Apply changes</button>
            {listArticles}
        </>)
}

export default Articles