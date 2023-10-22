import React from "react";
import  ReactDOM  from "react-dom";
import Desktop from "./Home";
import Desktop3 from "./Item";

import Desktop5 from "./Payment";
import Desktop6 from "./Bid";
import data from "./client_secret_560353721263-174578pe7vnpt5o4dosc812ljfi8inbd.apps.googleusercontent.com"
import Login from "./Login";
import {Route,BrowserRouter,Switch} from "react-router-dom"
import "./index.css"
import { GoogleOAuthProvider } from "@react-oauth/google";
import Chatpage from "./Chatpage";

function App (){
    return (
        <>
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><Login/></Route>
                <Route path="/home"><Desktop/></Route>
                <Route path="/item"><Desktop3/></Route>
                <Route path="/bid"><Desktop6/></Route>
                <Route path="/payment"><Desktop5/></Route>
                <Route path="/chat"><Chatpage/></Route>
            </Switch>
        </BrowserRouter>
       
       
        </>
        )
    
}

ReactDOM.render(<GoogleOAuthProvider clientId={data.web.client_id} ><App/></GoogleOAuthProvider>,document.getElementById('root'))