import React from "react";
import  ReactDOM  from "react-dom";
import Desktop from "./Home";
import Desktop3 from "./Item";

import Desktop5 from "./Payment";
import Desktop6 from "./Bid";

import Login from "./Login";
import {Route,BrowserRouter,Switch} from "react-router-dom"
import "./index.css"

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
            </Switch>
        </BrowserRouter>
       
       
        </>
        )
    
}

ReactDOM.render(<App/>,document.getElementById('root'))