import React from "react";
import  ReactDOM  from "react-dom";
import Desktop from "./Desktop";
import Desktop3 from "./Desktop3";
import Desktop4 from "./Desktop4";
import Desktop5 from "./Desktop5";
import Desktop6 from "./Desktop6";
import Desktop7 from "./Desktop7";
import Desktop8 from "./Desktop8";
import Login from "./Login";
import {Route,BrowserRouter,Switch} from "react-router-dom"


function App (){
    return (
        <>
        <BrowserRouter>
            <Switch>
                <Route exact path="/"><Login/></Route>
                <Route path="/home"><Desktop/></Route>
            </Switch>
        </BrowserRouter>
       
       
        </>
        )
    
}

ReactDOM.render(<App/>,document.getElementById('root'))