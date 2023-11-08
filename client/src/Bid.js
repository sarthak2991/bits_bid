import React from 'react'

import currency from "./assets/Group.png"
import search from "./assets/Search.png"
import home from "./assets/Home.png"
import chat from "./assets/Speech Bubble.png"
import heart from "./assets/Heart.png"
import cart from "./assets/Shopping Cart.png"
import euro from "./assets/Euro Money.png"
import settings from "./assets/Settings.png"
import account from "./assets/Account.png"
import forward from "./assets/Forward.png"

const Desktop6 = () => {
  return (
    <div className="biddesktop">
    <div className="div">
      <div className="overlap">
        <img className="image" alt="Image" src="image-2.png" />
      </div>
      <div className="overlap-group">
        <p className="text-wrapper">At what price would you like to buy this product?</p>
        <div className="div-wrapper">
          <input className='text-wrapper-2' placeholder='Enter your amount'></input>
        </div>
        <div className="group">
          <div className="overlap-group-2">
            <div className="rectangle" />
            <div className="lowest-bid">Lowest Bid:&nbsp;&nbsp;&nbsp;&nbsp; 799</div>
            <img className="img" alt="Group" src={currency} />
          </div>
        </div>
        <div className="overlap-wrapper">
          <div className="overlap-2">
            <div className="rectangle-2" />
            <div className="current-bid">Current Bid:&nbsp;&nbsp;&nbsp;&nbsp; 799</div>
            <img className="group-2" alt="Group" src={currency} />
          </div>
        </div>
        <div className="overlap-group-wrapper">
          <div className="overlap-3">
            <div className="rectangle-2" />
            <p className="highest-bid">
              <span className="span">
                Highest Bid:&nbsp;&nbsp;&nbsp;&nbsp; 799
                <br />
              </span>
              <span className="text-wrapper-3">
                <br />
              </span>
            </p>
            <img className="group-3" alt="Group" src={currency} />
          </div>
        </div>
        <div className="text-wrapper-4">About us</div>
        <div className="text-wrapper-5">Privacy policy</div>
        <div className="text-wrapper-6">Contact Us</div>
      </div>
      <div className="overlap-4">
        <div className="group-4">
          <div className="overlap-5">
          <div className="search-bar">
          <input className='search-bar' placeholder='What are you looking for?' style={{fontSize:'2rem'}}></input>
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
              <img className="account" alt="Account" src={account} />
            

            </div>
        </div>
        
      </div>
      <p className="text-wrapper-8">Prestige PKGSS 1.7L 1500W Electric Kettle (Stainless Steel)</p>
      <img className="forward" alt="Forward" src={forward} />
    </div>
  </div>

  )
}

export default Desktop6