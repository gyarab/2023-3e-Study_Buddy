import React, {useEffect, useState} from "react";
import ReactQuill from "react-quill";
import 'react-quill/dist/quill.snow.css'
import {Navigate} from "react-router-dom";

function UpdateArticle(){

    const [nextPage, setNextPage] = useState(false);
    const [article, setArticle] = useState('');
    const [title, setTitle] = useState('');

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

    useEffect(() => {
        setArticle(JSON.parse(window.localStorage.getItem('SET_ARTICLE')));
        setTitle(JSON.parse(window.localStorage.getItem('SET_TITLE')));
    },[])

    const handelOnClick=(e)=>{
        e.preventDefault()
        fetch("http://localhost:8080/api/v1/article/title/"+title,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:article
        })
        setNextPage(true);
    }


    if(nextPage){
        return  <Navigate to={"/"}/>;
    }

    return (
        <div className="auth-form-container body-sec">
            <div className={"article-settings"}>
                <ReactQuill modules={module} theme={"snow"} value={article} placeholder="Text" onChange={setArticle}/>
            </div>
            <div className={"bottom-button"}>
                <div className={"article-buttons"}>
                    <div className={"article-button-update"}>
                        <button onClick={handelOnClick}>Update</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UpdateArticle