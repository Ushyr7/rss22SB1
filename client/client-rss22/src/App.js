import axios from "axios";
import React, { useState, useEffect } from "react"


function App() {
  const [InputValueGet, setInputValueGet] = useState("");
  const [InputValueRemove, setInputValueRemove] = useState("");
  const [InputValueAdd, setInputValueAdd ] = useState("");
  const [ListArticle, setListArticle] = useState("");

  const[Article, setArticle] = useState("");
  const[ArticleToDelete, setArticleToDeleteResponse] = useState("");
  const[FluxToInsert, setFluxResponse] = useState("");

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

  const getInputValueAddArticle = (event) => {
    setInputValueAdd(event.target.value);
  }

  const handleGetArticle = () => {
    axios.get("/rss22/resume/html/" + InputValueGet, {"application" : "text/html"}).catch(function (error) {
      if (error.response) {
        setArticle("Erreur " + error.response.status + "... " + error.response.data);
      }
    }).then(response => {
      if(InputValueGet !== "") {
        setArticle(response.data);
      }
    });
  }

  const handleDeleteArticle = () => {
    axios.delete("/rss22/delete/" + InputValueRemove).catch(function (error) {
      if (error.response) {
        setArticleToDeleteResponse( error.response.status + "... " + error.response.data);
      }
    }).then(response => {
      if(InputValueRemove !== "") {
        setArticleToDeleteResponse(response.data + " supprimé !");
      }
    });
  }

  const handleInsert = () => {

    axios.post("/rss22/insert",InputValueAdd, {headers: {'Content-Type': 'application/xml'}}).catch(function (error) {
      if (error.response) {
        setFluxResponse( "Erreur " + error.response.status);
      }
    }).then(response => {
      if(InputValueAdd !== "") {
        setFluxResponse(response.data);
      }
    });
    console.log("In")
  }

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
              <input placeholder="Guid de l'article à lire"type="text" required onChange={getInputValueGetArticle}></input>
              <button class="btn green darken-2" onClick={handleGetArticle}>Obtenir</button>
            </div>
        </div>
      </div>
    </div>

    <div class="col s12 m6">
      <div class="card grey lighten-4">
        <div class="card-content black-text">
          <span class="card-title">Supprimer un article :</span>
          <div>
            <input placeholder="Guid de l'article à supprimer" type="text" required onChange={getInputValueRemoveArticle}></input>
            <button class="btn green darken-2" onClick={handleDeleteArticle}>Supprimer</button>
            <div class="container" dangerouslySetInnerHTML={{__html: ArticleToDelete}}/>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="card white">
        <div class="card-content black-text">
            <div
              dangerouslySetInnerHTML={{__html: Article}}
            />
        </div>
      </div>
  <div class="container" dangerouslySetInnerHTML={{__html: ListArticle}}/>
  <div class="col s12 m6">
      <div class="card grey lighten-4">
        <div class="card-content black-text">
          <span class="card-title">Ajouter un article en format XML : </span>
          <div>
            <input type="text" required onChange={getInputValueAddArticle}></input>
            <button class="btn green darken-2" onClick={handleInsert}>ajouter</button>
            <div>{FluxToInsert}</div>
          </div>
        </div>
      </div>
    </div>

</body>
</html>
)};

export default App;
