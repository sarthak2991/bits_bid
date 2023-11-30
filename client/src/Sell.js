import React, { useState } from 'react'

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
    
const Sell = () => {
    const [count,setCount] = useState(0) 
   const [categoryId,setCategory] = useState(0) 
   const [description,setDescription] = useState('')
   const [name,setName] = useState('')
   const [price,setPrice] = useState(0)
   const id = localStorage.getItem('id')
   const status = 0
   const [created,setCreated] = useState('') 
   const token = localStorage.getItem('token')
    const handleUpload = () => {
        axios.post("http://localhost:8080/api/v1/products/",{categoryId,description,name,price,id,status},{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{  if(res.data.hasOwnProperty('result')){
        const productId = res.data.result.id
        
        setCreated(res.data.result.createdAt)
        alert('Image uploaded successfully ')
          }
          else{
            alert(res.data.msg)
          }
        })
      }   
      const [selectedOption, setSelectedOption] = useState('');

  const handleSelectChange = (event) => {
    setCategory(parseInt(event.target.value[6]))
    setSelectedOption(event.target.value);
  };
    function handleChange(e) {
        e.preventDefault()
        //e.target.files.forEach(myFunction)
        const objectLength = Object.keys(e.target.files).length;
        for(let i = 0;i<objectLength;i++){
            //console.log(e.target.files[i])
            images.push(URL.createObjectURL(e.target.files[i]))
        }
        
    
        }
  return (
    <div className="itemdesktop">
    <div className="div">
      <div className="overlap">
        <div className="rectangle" ></div>
        <div className="flexcontainer">
          <p className="text">
            <span className="text-wrapper">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span className="span">
              &nbsp;&nbsp;<input placeholder='Enter base price' value={price} onChange={(e)=>{setPrice(e.target.value)}}/>
              <br />
              <span className='text-wrapper-3'>
              <textarea value={description} onChange={(e)=>{setDescription(e.target.value)}} style={{'width':'40rem','height':'13rem','margin':'1rem','textAlign':'left','padding':'0.5rem','wordWrap':'break-word','wordBreak':'break-all'}} placeholder='Enter description of product'/>
              </span>
            </span>
          </p>
          
        </div>
        <img className="group" alt="Group" src={currency} />
      </div>
      <input className="p" placeholder='Enter name of product' value={name} onChange={(e)=>{setName(e.target.value)}}/>
      <div className='p1'>
      <label htmlFor="mySelect">Select an option : </label>
      <select id="mySelect" value={selectedOption} onChange={handleSelectChange}>
        <option value="">Select a category</option>
        <option value="option1">Stationary Supplies</option>
        <option value="option2">Essentials</option>
        <option value="option3">Electrical Appliances</option>
        <option value="option4">Fashion</option>
        <option value="option5">Electronic Gadgets</option>
        <option value="option6">Sports Equipments</option>
        <option value="option7">Room Decor</option>
        <option value="option8">Gym Supplies</option>
      </select>

     </div>
         <div className="overlap-group">
        <div className="rectangle-2" />
        <button className='forwardbutton' onClick={()=>{if(count<images.length){setCount(count+1)}else{setCount(0)}}}><img className="forward" alt="Forward" src={forward1}  /></button>
      
        {(images.length!==0)?(<img className='image' src={images[count]}/>):(<div className='image'></div>)}
        <input className='chooseimage' multiple type='file' onChange={(e)=>{handleChange(e)}}></input>
        <button className='upload' onClick={(e)=>{handleUpload(e)}}  style={{'height':'2rem'}}>Upload</button>
      </div>
      <div className="add-to-cart-start-wrapper">
        <button className="add-to-cart-start" onClick={()=>{if(created!==""){alert("bidding started")}else{alert("please fill all details")}}}>Start Bidding</button>
      </div>
      <div className="div-wrapper">
        <button className="text-wrapper-4" >Stop Bidding</button>
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

export default Sell