import axios from 'axios'
import React, { useEffect, useState } from 'react'
import currency from "./assets/Group.png"
import menu from "./assets/Menu.png"

const Desktop5 = () => {
  const name = localStorage.getItem('name')
    const hostel = localStorage.getItem('hostel')
    const email = localStorage.getItem('email')
    const phone = localStorage.getItem('phone')
    const balance = localStorage.getItem('balance')
    const bidId = localStorage.getItem('bidId')
    const [seller,setSeller] = useState([])
    useEffect(()=>{
      axios.get("http://localhost:8080/api/v1/users/profile?id="+productDetails.sellerId,{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
        if(res.data.hasOwnProperty('msg')){
          alert(res.data.msg)
          window.location.href = "/order"
        }
      setSeller(res.data.result)
        
        //console.log(seller)
      })
    },[])
    const handleClick = () => {
   
      axios.post("http://localhost:8080/api/v1/bids/"+bidId+"/buyout",{},{headers : {'Authorization': `Bearer ${token}`}}).then((res)=>{
       
      if(res.data.hasOwnProperty('result')){alert(res.data.result)} else{alert(res.data.msg)}}  ) 
     
        axios.get("http://localhost:8080/api/v1/users/profile",{headers : {'Authorization': `Bearer ${token}`}}).then(
          (res)=>{
            localStorage.setItem('balance',res.data.result.balance)
          }
        )
    }
    const token = localStorage.getItem('token')
  const productDetails = JSON.parse(localStorage.getItem('productdetails'))
  const bidAmount = localStorage.getItem('bidAmount')
  return (
    <div className="paymentdesktop">
    <div className="div">
      <div className="overlap">
        <div className="rectangle" />
        <div className="text-wrapper">Pay using wallet</div>
        <div className="rectangle-2" />
        <div className="wallet-balance">Wallet Balance:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {balance} BITScoin</div>
        <div className="rectangle-3" />
        <div className="rectangle-4" />
        <div className="rectangle-5" />
        <p className="your-bid">
          <span className="span">
            Your Bid:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {bidAmount}
            <br />
          </span>
          <span className="text-wrapper-2">
            <br />
          </span>
        </p>
        <p className="final-bid">
          <span className="span">
            Final Bid:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {bidAmount}
            <br />
          </span>
          <span className="text-wrapper-2">
            <br />
          </span>
        </p>
        <img className="group" alt="Group" src={currency} />
        <img className="img" alt="Group" src={currency} />
        <img className="group-2" alt="Group" src={currency} />
        <img className="group-3" alt="Group" src={currency} />
        <div className="div-wrapper">
          <button className="text-wrapper-3" onClick={()=>{handleClick()}}>Pay now</button>
        </div>
        <div className="overlap-group-wrapper">
          <div className="overlap-group">
            <div className="pay-to-seller">Pay&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{bidAmount} to Seller</div>
            <img className="group-4" alt="Group" src={currency} />
          </div>
        </div>
        <div className="rectangle-6" />
        <div className="rectangle-7" />
        <div className="rectangle-8" />
        <div className="rectangle-9" />
        <div className="text-wrapper-4">{name}</div>
        <div className="text-wrapper-5">{phone}</div>
        <div className="text-wrapper-6">{email}</div>
        <div className="text-wrapper-7">{hostel}</div>
        <div className="rectangle-10" />
        <div className="rectangle-11" />
        <div className="rectangle-12" />
        <div className="rectangle-13" />
        <div className="text-wrapper-8">{seller.name}</div>
        <div className="text-wrapper-9">{seller.contactNo}</div>
        <div className="text-wrapper-10">{seller.email}</div>
        <div className="text-wrapper-11">{seller.hostel}</div>
      </div>
      <div className="overlap-2">
        <div className="rectangle-14" />
        <div className="text-wrapper-12">Cart</div>
        <div className="rectangle-15" />
        <img className="kettle-and-cup-on" alt="Kettle and cup on" src="kettle-and-cup-on-wall-background.png" />
        <p className="p">{productDetails.name}</p>
        <div className="rectangle-16" />
        <div className="original-bid">Original Bid:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {productDetails.price}</div>
        <img className="group-5" alt="Group" src={currency} />
      </div>
      <div className="overlap-3">
        <div className="text-wrapper-13">Payment Gateway</div>
      </div>
      </div>
  </div>

  )
}

export default Desktop5