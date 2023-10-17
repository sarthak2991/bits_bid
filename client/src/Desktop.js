import React from 'react'
import "./desktop.css"
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
  const icons = [search,home,chat,heart,cart,euro,settings,account]
  return (
    <div className="desktop">
      
    <div className="homediv">
   
      {choices.map((index,key)=>{
        
        return (<div key={key} className="homeoverlap">
        <div className="homegroup">
          <div className="homeoverlap-group">
            <div className="homerectangle" />
            <img className="homeimg" alt="Rectangle" src={images[key]} />
          </div>
        </div>
        <div className="hometext-wrapper">{index}</div>
      </div>)})}
      
      
       <div className='navbar'><img className="forward" alt="Forward" src={forward} /><input className='homeSearch'></input>{icons.map((index,key) => { return (<img key={key} className="homeicon" alt="search" src={index} />)})}</div>
    
    </div>
  </div>
);

 
}

export default Desktop