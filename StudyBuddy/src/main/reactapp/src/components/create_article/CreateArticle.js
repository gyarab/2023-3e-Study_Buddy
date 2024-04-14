import React, {useEffect, useState} from "react";
import ReactQuill from "react-quill";
import 'react-quill/dist/quill.snow.css'
import {Navigate} from "react-router-dom";
import SubtitlesIcon from '@mui/icons-material/Subtitles';

function CreateArticle(){

    const [title, setTitle] = useState('');
    const [article, setArticle] = useState('');
    const [articleSubject, setArticleSubject] = useState(1);
    const [subjects, setSubjects] = useState([]);
    const [nextPage, setNextPage] = useState(false);

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
        setNextPage(true);
    }
    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleSubjectChange = (event) => {
        setArticleSubject(event.target.value);
        console.log(event.target.value)
    };

    useEffect(()=>{
        setTitle(JSON.parse(window.localStorage.getItem('SET_TITLE')))
        setArticle(JSON.parse(window.localStorage.getItem('SET_ARTICLE')))
    },[])

    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/subjects")
            .then(res=>res.json())
            .then((result)=>{
                setSubjects(result)
            })
        window.localStorage.setItem('SET_TITLE', JSON.stringify(title))
        window.localStorage.setItem('SET_ARTICLE', JSON.stringify(article))
    },[article, articleSubject, title])

    const listSubjects = subjects.map(subject => <option value={subject.id}>{subject.name}</option>)


    if(nextPage){
        return  <Navigate to={"/"}/>;
    }

    return (
        <div className="auth-form-container body-sec">
            <div className={"article-settings"}>
                <div className={"title-article"}>
                    <SubtitlesIcon className={"input-icon-sm"}/><input value={title} type="text" placeholder="title" onChange={handleTitleChange} />

                    <div>
                        <div>
                            <select onChange={handleSubjectChange}>
                                {listSubjects}
                            </select>
                        </div>
                    </div>
                </div>
                <ReactQuill modules={module} theme={"snow"} value={article} placeholder="Text" onChange={setArticle}/>
            </div>

            <div className={"bottom-button"}>
                <div className={"article-buttons"}>
                    <div className={"article-body"}>
                        <button onClick={handelOnClick}>Post new article</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CreateArticle