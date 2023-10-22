import React from 'react'
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


const Chatpage = () => {
  return (
    <div className="chatpage_desktop">
      <div className="div">
        <div className="overlap">
          <div className="group">
            <div className="overlap-group">
           
              
              
            
           
             
             
              
              <div className="overlap-7">
                
                <input className='rectangle-6' placeholder='Type..'/>
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
              <img className="account" alt="Account" src={account} />
            </div>
          </div>
          <img className="forward" alt="Forward" src={forward} />
          
        </div>
      </div>
    </div>

  )
}

export default Chatpage