import React, { useEffect, useState } from 'react'
import "./index.css"

import secure from "./assets/Secure lock and key.png"
import forward from "./assets/Forward.png"
import search from "./assets/Search.png"
import home from "./assets/Home.png"
import chat from "./assets/Speech Bubble.png"
import heart from "./assets/Heart.png"
import cart from "./assets/Shopping Cart.png"
import euro from "./assets/Euro Money.png"
import settings from "./assets/Settings.png"
import account from "./assets/Account.png"
import axios from 'axios'


const Chatpage1 = () => {
  const [searchItem,setSearch] = useState('')
  const[suggestions,setSuggestion] = useState([])
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
  const handleSuggestionClick = (suggestion) => {
    setSearch(suggestion);
    setSuggestion([]);
  };
  const [chathistory,setChathistory]= useState([])
  const[message,setMessage] = useState("")
  const token = localStorage.getItem('token')
  const productId = localStorage.getItem('productid')
  const sellerid = localStorage.getItem('userid')
const userid = localStorage.getItem('id')

  useEffect(()=>{axios.get("http://localhost:8080/api/v1/chat/"+productId+'/'+sellerid+'/get',{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
    if(res.data.hasOwnProperty('result')){
       setChathistory(res.data.result)
      }
      else{
        alert(res.data.msg)
      }
    })},[])
  return (
    <div className="chatpage_desktop">
      <div className="div">
        <div className="overlap">
          <div className="group">
            <div className="overlap-group">
           
              
              
            
           
             
             
              
              <div className="overlap-7">
                Your chats<hr/>
          
      {chathistory.map((item,key)=>{
      
       return(
        <>

        {(item.sender === parseInt(userid))?(<div  key={key}>{item.msg}</div>):(<div  style={{'textAlign':'right'}}   key={key}>{item.msg}<br/><br/></div>)}
        
        </>
       )})}
       <input className="text-wrapper-6" value={message} onKeyDown={(e)=>{if(e.key === "Enter"){axios.post("http://localhost:8080/api/v1/chat/send",{'to':sellerid,productId,'msg':message},{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
        alert(res.data.result)
        setMessage('')
       })}}} onChange={(e)=>{setMessage(e.target.value)}} placeholder='Type..'></input>
                </div>
              
            </div>
          </div>
          
        </div>
        <div className="group-5">
          <p className="text-wrapper-7">Your Chats are 100% Secured and Anonymous.</p>
          <img className="secure-lock-and-key" alt="Secure lock and key" src={secure} />
        </div>
        <div className="overlap-9">
          <div className="group-6">
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
      )}  <img className="search" alt="Search" src={search} />
        </div>
        <a href='/home'><img className="home" alt="Home" src={home} /></a>
            <a href='/chat'><img className="speech-bubble" alt="Speech bubble" src={chat} /></a>
            <div className="account-wrapper">
             <a href="/profile"> <img className="account" alt="Account" src={account} /></a>
            </div>
          </div>
          <a href="/home"><img className="forward" alt="Forward" src={forward} /></a>
          
        </div>
      </div>
    </div>

  )
}

export default Chatpage1