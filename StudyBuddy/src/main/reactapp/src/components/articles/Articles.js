import Card from "./card/Card";
import './Articles.css';
import React, {useEffect, useState} from "react";

function Articles(){

    const [articles, setArticles]=useState([]);
    const [subjects, setSubjects]=useState([]);

    useEffect(()=>{

        fetch("http://localhost:8080/api/v1/article")
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

    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/article")
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
        if(event.target.value==0){
            fetch("http://localhost:8080/api/v1/article")
                .then(res=>res.json())
                .then((result)=>{
                    setArticles(result);
                })
        } else {
            fetch("http://localhost:8080/api/v1/article/" + event.target.value)
                .then(res=>res.json())
                .then((result)=>{
                    setArticles(result);
                })
        }
    };

    const listArticles = articles.map(article => <><Card title={article.title} text={article.article} id={article.id} /><br/></>)
    const listSubjects = subjects.map(subject => <option value={subject.id}>{subject.name}</option>)

    return(
        <>
            <select className={"selectSubject"} onChange={handleSubjectChange}>
                <option value={0} >All subjects</option>
                {listSubjects}
            </select>
            {listArticles}
        </>)
}

export default Articles