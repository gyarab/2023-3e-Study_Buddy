import React, {useEffect, useState} from "react";
import ReactQuill from "react-quill";
import 'react-quill/dist/quill.snow.css'
import Card from "../../articles/card/Card";
import {Select} from "@mui/material";

function CreateArticle(){

    const [title, setTitle] = useState('');
    const [article, setArticle] = useState('');
    const [articleSubject, setArticleSubject] = useState(1);
    const [subjects, setSubjects] = useState([]);

    const toolbarOptions = [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        ['link', 'image', 'video'], //, 'formula'

        [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
        [{ 'script': 'sub'}, { 'script': 'super' }],
        [{ 'indent': '-1'}, { 'indent': '+1' }],

        [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
        [{ 'align': [] }]
    ];

    const module = {
        toolbar:toolbarOptions
    }

    const handelOnClick=(e)=>{
        e.preventDefault()
        const newArticle={title, article, articleSubject}
        fetch("http://localhost:8080/api/v1/article",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(newArticle)
        })
    }
    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleSubjectChange = (event) => {
        setArticleSubject(event.target.value);
    };

    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/subjects")
            .then(res=>res.json())
            .then((result)=>{
                setSubjects(result)
            })
    },[])

    const listSubjects = subjects.map(subject => <option value={subject.id}>{subject.name}</option>)

    return (
        <div className="auth-form-container">
            <h1>Create and post your own article</h1>
            <input value={title} type="text" placeholder="title" onChange={handleTitleChange} />
            <br/>
            <ReactQuill modules={module} theme={"snow"} value={article} placeholder="Text" onChange={setArticle}/>
            <select className={"selectSubject"} onChange={handleSubjectChange}>
                {listSubjects}
            </select>
            <button onClick={handelOnClick}>Post new article</button>
        </div>
    );
}

export default CreateArticle