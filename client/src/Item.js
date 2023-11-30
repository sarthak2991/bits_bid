import React from 'react'

import search from "./assets/Search.png"
import home from "./assets/Home.png"
import chat from "./assets/Speech Bubble.png"
import heart from "./assets/Heart.png"
import cart from "./assets/Shopping Cart.png"
import euro from "./assets/Euro Money.png"
import settings from "./assets/Settings.png"
import account from "./assets/Account.png"
import forward from "./assets/Forward.png"
import forward1 from "./assets/Forward (2).png"
import currency from "./assets/Group.png"

const Desktop3 = () => {
  const productDetails = JSON.parse(localStorage.getItem('productdetails'))
  //console.log(productDetails)
  const handleClick =()=>{window.location.href="/bid"}
  return (
    <div className="itemdesktop">
    <div className="div">
      <div className="overlap">
        <div className="rectangle" />
        <div className="flexcontainer">
          <p className="text">
            <span className="text-wrapper">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span className="span">
              &nbsp;&nbsp;{productDetails.price}
              <br />
              <span className='text-wrapper-3'>
              {productDetails.description}
              </span>
            </span>
          </p>
          
        </div>
        <img className="group" alt="Group" src={currency} />
      </div>
      <p className="p">{productDetails.name}</p>
      <div className="overlap-group">
        <div className="rectangle-2" />
        <img className="forwardbutton" alt="Forward" src={forward1} />
        
        <img className="image" alt="Image" src="image-1.png" />
      </div>
      <div className="div-wrapper">
        <button className="text-wrapper-4" >Chat with Seller</button>
      </div>
      <div className="add-to-cart-start-wrapper">
        <button className="add-to-cart-start" onClick={()=>{handleClick()}}>Start Bidding</button>
      </div>
      <div className="group-2">
        <div className="overlap-3">
          <div className="overlap-3" >
          <input className='search-bar' placeholder='What are you looking for?' style={{fontSize:'2rem'}}></input>
          <img className="search" alt="Search" src={search} />
        </div>
          <img className="search" alt="Search" src={search} />
        </div>
        <a href='/home'><img className="home" alt="Home" src={home} /></a>
        <img className="heart" alt="Heart" src={heart} />
        <img className="shopping-cart" alt="Shopping cart" src={cart} />
        <a href='/chat'><img className="speech-bubble" alt="Speech bubble" src={chat} /></a>
        <img className="euro-money" alt="Euro money" src={euro} />
        <img className="settings" alt="Settings" src={settings} />
        <div className="account-wrapper">
          <a href='/profile'><img className="account" alt="Account" src={account} /></a>
        </div>
      </div>
      <div className="group-5">
        <div className="rectangle-5" />
      </div>
      <div className="overlap-wrapper">
        <div className="overlap-4">
          </div>
      </div>
      <a href="/home"><img className="forward-2" alt="Forward" src={forward} /></a>
    </div>
  </div>

  )
}

export default Desktop3