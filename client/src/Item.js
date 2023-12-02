import React from 'react'
import { useState } from 'react'
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
import axios from 'axios'

const images = []
const Desktop3 = () => {
  const [count,setCount] = useState(0) 
  const [searchItem,setSearch] = useState('')
  const token = localStorage.getItem('token')
  const[suggestions,setSuggestion] = useState([])
  const productDetails = JSON.parse(localStorage.getItem('productdetails'))
  const handleSuggestionClick = (suggestion) => {
    setSearch(suggestion);
    setSuggestion([]);
  };
  const handleSearch = (e) => {
    e.preventDefault()
    setSearch(e.target.value);
    axios.get("https://big-omc1.onrender.com/api/v1/products/search?name="+searchItem,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
      //console.log(res.data.result);
      if(res.data.hasOwnProperty('result')){
        const objectLength = Object.keys(res.data.result).length;
        for(let i = 0;i<objectLength;i++){
          if(!suggestions.includes(res.data.result[i].name)){
          suggestions.push(res.data.result[i].name)}
        }
        
      }})
  }
  
  //console.log(productDetails)
  const handleClick =()=>{window.location.href="/bid"}
  const handleChat = ()=>{
    localStorage.setItem('productid',productDetails.id)
    localStorage.setItem('userid',productDetails.sellerId)
    window.location.href = '/personal'
  }
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
        <button className='forwardbutton' onClick={()=>{if(count<images.length){setCount(count+1)}else{setCount(0)}}}><img className="forward" alt="Forward" src={forward1}  /></button>
      
        {(images.length!==0)?(<img className='image' src={images[count]}/>):(<div className='image'></div>)}
        <img className="image" alt="Image" src="image-1.png" />
      </div>
      <div className="div-wrapper">
        <button className="text-wrapper-4" onClick={()=>{handleChat()}}>Chat with Seller</button>
      </div>
      <div className="add-to-cart-start-wrapper">
        <button className="add-to-cart-start" onClick={()=>{handleClick()}}>Start Bidding</button>
      </div>
      <div className="group-2">
        <div className="overlap-3">
          <div className="overlap-3" >
          <input className='search-bar' value={searchItem} onChange={(e)=>{handleSearch(e)}} onKeyUp={(e)=>{if(e.key === "Enter"){ 
           axios.get("https://big-omc1.onrender.com/api/v1/products/search?name="+searchItem,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
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
          <img className="search" alt="Search" src={search} />
        </div>
        <a href='/home'><img className="home" alt="Home" src={home} /></a>
        <a href='/chat'><img className="speech-bubble" alt="Speech bubble" src={chat} /></a>
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