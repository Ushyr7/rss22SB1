import axios from "axios";
import React, { useState, useEffect } from "react"


function App() {
  const [InputValueGet, setInputValueGet] = useState("");
  const [InputValueRemove, setInputValueRemove] = useState("");
  const [ListArticle, setListArticle] = useState("");

  useEffect(()=>{
    axios.get("/rss22/resume/html", {"application" : "text/html"}).then(response => {
      setListArticle(response.data);
    });
  }, [])

  const getInputValueRemoveArticle = (event)=>{
    setInputValueRemove(event.target.value);
  };


  const getInputValueGetArticle = (event)=>{
    setInputValueGet(event.target.value);
  };

  return (
    
<html lang="fr">
  <head>
    <title> Client - RSS22 </title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"/>
  </head>
  <body>
    
  <nav>
    <div class="nav-wrapper green">
      <a href=" " class="brand-logo center">Client - RSS22</a>
    </div>
  </nav>

  <div class="row">
    <div class="col s12 m6">
      <div class="card white">
        <div class="card-content black-text">
          <span class="card-title">Obtenir un article :</span>
            <div>
              <input placeholder="Guid de l'article à lire"type="text" onChange={getInputValueGetArticle}></input>
              <p>{InputValueGet}</p>
              <button class="btn green darken-2">Obtenir</button>
            </div>
        </div>
      </div>
    </div>

    <div class="col s12 m6">
      <div class="card grey lighten-4">
        <div class="card-content black-text">
          <span class="card-title">Supprimer un article :</span>
          <div>
            <input placeholder="Guid de l'article à supprimer" type="text" onChange={getInputValueRemoveArticle}></input>
            <p>{InputValueRemove}</p>
            <button class="btn green darken-2">Supprimer</button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="container" dangerouslySetInnerHTML={{__html: ListArticle}}/>

</body>
</html>
)};

export default App;
