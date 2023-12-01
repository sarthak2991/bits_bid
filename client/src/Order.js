import React, { useEffect, useState } from 'react'
import axios from "axios"

import stationary from "./assets/stationary.png"
import essential from "./assets/essential.png"
import electrical_a from "./assets/electrical_appliances.png"
import fashion from "./assets/fashion.png"
import electrical_g from "./assets/electrical_gadgets.png"
import decor from "./assets/decor.png"
import sports from "./assets/sports.png"
import gym from "./assets/gym.png"
import forward from "./assets/Forward.png"
import search from "./assets/Search.png"
import home from "./assets/Home.png"
import chat from "./assets/Speech Bubble.png"
import heart from "./assets/Heart.png"
import cart from "./assets/Shopping Cart.png"
import euro from "./assets/Euro Money.png"
import settings from "./assets/Settings.png"
import account from "./assets/Account.png"

const Order = () => {
  const[suggestions,setSuggestion] = useState([])
  useEffect(()=>{axios.get("http://localhost:8080/api/v1/users/profile",{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
  
        setChoice(res.data.result.bids)
        
    }

  )},[])
  const handleSearch = (e) => {
    e.preventDefault()
    setSearch(e.target.value);
    axios.get("http://localhost:8080/api/v1/products/search?name="+searchItem,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
      //console.log(res.data.result);
      if(res.data.hasOwnProperty('result')){
        const objectLength = Object.keys(res.data.result).length;
        for(let i = 0;i<objectLength;i++){
          if(!suggestions.includes(res.data.result[i].name)){
          suggestions.push(res.data.result[i].name)}
        }
        
      }})
  }
  const id= localStorage.getItem('id')
  const loggedin = localStorage.getItem("loggedin")
  const token = localStorage.getItem('token')
  const [searchItem,setSearch] = useState('')
  const [choices,setChoice]=useState([]) 
  const handleSuggestionClick = (suggestion) => {
    setSearch(suggestion);
    setSuggestion([]);
  };
  const handleClick =  (e) => {e.preventDefault();
    const productId = e.target.alt
  //console.log(productId)
  axios.get("http://localhost:8080/api/v1/products/"+productId,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
   const productDetails = JSON.stringify(res.data.result)
localStorage.setItem('productdetails',productDetails)
console.log(choices[0].productId)
for(let i=0;i<choices.length;i++){
  
    if(choices[i].productId === parseInt(productId) && choices[i].isFrozen){
      localStorage.setItem("bidId",choices[i].id)
      localStorage.setItem('bidAmount',choices[i].bidAmount)
        window.location.href="/payment"
        break
    }
    else{
        window.location.href = "/bid"
    }
    
}

})
  }

 
  return (
    <>{
      (loggedin)?(
    
    <div className="homedesktop">
      
    <div className="homediv">
   {(choices.length === 0)?(<div className='homeoverlap' style={{'fontSize':'2rem'}}>No results to display</div>):(<>{choices.map((index,key)=>{
     
        return (<button onClick={(e)=>{handleClick(e)}}   key={key} className="homeoverlap">
        <div className="homegroup">
          <div className="homeoverlap-group">
            <div className="homerectangle" />
            <img className="homeimg" alt={index.productId} src={index.img_urls} />
          </div>
        </div>
        
        <div  className="hometext-wrapper">{(index.isFrozen)?(<>{(index.sellerId === parseInt(id))?(<>Sold</>):(<>Buy</>)}</>):(<>{(index.sellerId === parseInt(id))?(<>Not Sold</>):(<>Bid</>)}</>)} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Bid:{index.bidAmount}</div>
      </button>)})}
      </>)}
      
     
      <div className="group-2">
        <div className="overlap-3" >
        <input className='search-bar' value={searchItem} onChange={(e)=>{handleSearch(e)}} onKeyUp={(e)=>{if(e.key === "Enter"){ 
           axios.get("http://localhost:8080/api/v1/products/search?name="+searchItem,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
            const products = JSON.stringify(res.data.result)
    
    localStorage.setItem('products',products)
    window.location.href = "/product"
  
           })
            setSearch('')}}} placeholder='What are you looking for?' style={{fontSize:'2rem'}}></input>
          {suggestions.length > 0 && (
        <ul className="suggestions-list">
          {suggestions.map((suggestion, index) => (
            <li key={index} onClick={() => handleSuggestionClick(suggestion)}>
              {suggestion}
            </li>
          ))}
        </ul>
      )}
<img className="search" alt="Search" src={search} />
        </div>
        <a href='/home'><img className="home" alt="Home" src={home} /></a>
        <a href='/chat'><img className="speech-bubble" alt="Speech bubble" src={chat} /></a>
        <div className="account-wrapper">
          <a href='/profile'><img className="account" alt="Account" src={account} /></a>
        </div>
      </div>
    </div>
  </div>):(<>{window.location.href="/"}</>)}</>
);

 
}

export default Order