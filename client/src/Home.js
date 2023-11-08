import React, { useState } from 'react'
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

const Desktop = () => {
  const choices = ["Stationary Supplies","Essentials","Electrical Appliances","Fashion","Electronic Gadgets","Sports Equipments","Room Decor","Gym Supplies"]
  const images = [stationary,essential,electrical_a,fashion,electrical_g,sports,decor,gym]
  const loggedin = localStorage.getItem("loggedin")
  const [searchItem,setSearch] = useState('')
  return (
    <>{
      (loggedin)?(
    
    <div className="homedesktop">
      
    <div className="homediv">
   
      {choices.map((index,key)=>{
        
        return (<button key={key} className="homeoverlap">
        <div className="homegroup">
          <div className="homeoverlap-group">
            <div className="homerectangle" />
            <img className="homeimg" alt="Rectangle" src={images[key]} />
          </div>
        </div>
        <div className="hometext-wrapper">{index}</div>
      </button>)})}
      
     
      <div className="group-2">
        <div className="overlap-3" >
          <input className='search-bar' value={searchItem} onChange={(e)=>{setSearch(e.target.value)}} onKeyUp={(e)=>{if(e.key === "Enter"){axios.post("./searchitem",{searchItem},{headers:{}}).then()}}} placeholder='What are you looking for?' style={{fontSize:'2rem'}}></input>
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
    </div>
  </div>):(<>{window.location.href="/"}</>)}</>
);

 
}

export default Desktop