import React from "react";
import  ReactDOM  from "react-dom";
import Desktop from "./Home";
import Desktop3 from "./Item";

import Desktop5 from "./Payment";
import Desktop6 from "./Bid";
import Login from "./Login";
import {Route,BrowserRouter,Switch} from "react-router-dom"
import "./index.css"
import { GoogleOAuthProvider } from "@react-oauth/google";
import Chatpage from "./Chatpage";
import Details from "./Details";
import Profile from "./Profile";
import Sell from "./Sell";
import Chatpage1 from "./Chatpage1";
import Product from "./Product";
import Order from "./Order";

function App (){
    return (
        <>
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><Login/></Route>
                <Route path="/home"><Desktop/></Route>
                <Route path="/buy"><Desktop3/></Route>
                <Route path="/bid"><Desktop6/></Route>
                <Route path="/payment"><Desktop5/></Route>
                <Route path="/chat"><Chatpage/></Route>
                <Route path="/personal"><Chatpage1/></Route>
                <Route path="/details"><Details/></Route>
                <Route path="/profile"><Profile/></Route>
                <Route path="/sell"><Sell/></Route>
                <Route path="/product"><Product/></Route>
                <Route path="/order"><Order/></Route>
            </Switch>
        </BrowserRouter>
       
       
        </>
        )
    
}

ReactDOM.render(<GoogleOAuthProvider clientId="560353721263-nnegf6lu2gmhe40g9m9aa4hp7jqsb3ai.apps.googleusercontent.com" ><App/></GoogleOAuthProvider>,document.getElementById('root'))