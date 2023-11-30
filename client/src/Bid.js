import React, { useEffect, useState } from 'react'

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
import axios from 'axios'

const Desktop6 = () => {
  const productDetails = JSON.parse(localStorage.getItem('productdetails'))
const [currentbid,setCurrent] = useState('')
  useEffect(()=>{axios.get("http://localhost:8080/api/v1/products/"+productId,{headers : {'Authorization': `Bearer ${token}`}}).then(
    (res)=>{
      const bids = []
      for(let i = 0;i<res.data.result.bids.length;i++){
        bids.push(res.data.result.bids[i].bidAmount)
      }
      setCurrent(Math.max(...bids))
    }
  )},[])
  const [bidAmount,setBid] = useState("")
  const token = localStorage.getItem('token')
  const productId = productDetails.id
  const name = productDetails.name
const handleClick = ()=>{
  axios.post("http://localhost:8080/api/v1/bids/",{bidAmount,productId},{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
    if(res.data.hasOwnProperty('result')){
      alert("bid placed successfully")
    }
    else{
      alert(res.data.msg)
    }
  })
}
const handleUpdate = ()=>{
  axios.put("http://localhost:8080/api/v1/bids/"+productId,{bidAmount,productId},{headers : {'Authorization': `Bearer ${token}`}}).then(
    (res)=>{
      if(res.data.hasOwnProperty('result')){
        alert("bid updated successfully")
      }
      else{
        alert(res.data.msg)
      }}
  )
}
  return (
    <div className="biddesktop">
    <div className="div">
      <div className="overlap">
        <img className="image" alt="Image" src="image-2.png" />
      </div>
      <div className="overlap-group">
        <p className="text-wrapper">Enter your bid for this product?</p>
        <div className="div-wrapper">
          <input className='text-wrapper-2' value={bidAmount} onChange={(e)=>{setBid(e.target.value)}} placeholder='Enter your amount'></input>
          <button className='submit' onClick={()=>{handleClick()}}>Submit</button>
          <button className='update' onClick={()=>{handleUpdate()}}>Update</button>
        </div>
        <div className="group">
          <div className="overlap-group-2">
            <div className="rectangle" />
            <div className="lowest-bid">Base Bid :  &nbsp;&nbsp;&nbsp;&nbsp; {productDetails.price}</div>
            <img className="img" alt="Group" src={currency} />
          </div>
        </div>
        <div className="overlap-wrapper">
          <div className="overlap-2">
            <div className="rectangle-2" />
            <div className="current-bid">Current Bid:&nbsp;&nbsp;&nbsp;&nbsp; {currentbid}</div>
            <img className="group-2" alt="Group" src={currency} />
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
              <a href='/profile'><img className="account" alt="Account" src={account} /></a>
            

            </div>
        </div>
        
      </div>
      <p className="text-wrapper-8">{productDetails.name}</p>
      <img className="forward" alt="Forward" src={forward} />
    </div>
  </div>

  )
}

export default Desktop6