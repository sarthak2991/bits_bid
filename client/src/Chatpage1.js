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
  const [chathistory,setChathistory]= useState([])
  const token = localStorage.getItem('token')
  const productID = localStorage.getItem('productid')
  const sellerid = localStorage.getItem('userid')
const userid = localStorage.getItem('id')
  useEffect(()=>{axios.get("http://localhost:8080/api/v1/chat/"+productID+'/'+sellerid+'/get',{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
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
       console.log(item.sender)
       return(
        <>
        {(item.sender === userid)?(<div  key={key}>{item.msg}</div>):(<div  style={{'textAlign':'right'}}   key={key}>{item.msg}<br/><br/></div>)}
        
        </>
       )})}
                </div>
              
            </div>
          </div>
          
        </div>
        <div className="group-5">
          <p className="text-wrapper-7">Your Chats are 100% Secured and Anonymous.</p>
          <img className="secure-lock-and-key" alt="Secure lock and key" src={secure} />
        </div>
        <div className="text-wrapper-8">About us</div>
        <div className="text-wrapper-9">Privacy policy</div>
        <div className="text-wrapper-10">Contact Us</div>
        <div className="overlap-9">
          <div className="group-6">
          <div className="overlap-3" >
          <input className='search-bar' placeholder='What are you looking for?' style={{fontSize:'2rem'}}></input>
          <img className="search" alt="Search" src={search} />
        </div>
        <a href='/home'><img className="home" alt="Home" src={home} /></a>
            <img className="heart" alt="Heart" src={heart} />
            <img className="shopping-cart" alt="Shopping cart" src={cart} />
            <a href='/chat'><img className="speech-bubble" alt="Speech bubble" src={chat} /></a>
            <img className="euro-money" alt="Euro money" src={euro} />
            <img className="settings" alt="Settings" src={settings} />
            <div className="account-wrapper">
             <a href="/profile"> <img className="account" alt="Account" src={account} /></a>
            </div>
          </div>
          <img className="forward" alt="Forward" src={forward} />
          
        </div>
      </div>
    </div>

  )
}

export default Chatpage1