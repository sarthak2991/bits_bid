import "bootstrap/dist/css/bootstrap.min.css";
import { input, Button } from "react-bootstrap";
import "./index.css";
import background from './assets/unsplash_5DD7-L4A4Uw.png'
import account from './assets/Account.png'
import { useState } from "react";
import axios from "axios";
const Profile = () => {
    const name = localStorage.getItem('name')
    const hostel = localStorage.getItem('hostel')
    const email = localStorage.getItem('email')
    const phone = localStorage.getItem('phone')
    const balance = localStorage.getItem('balance')
    const [add,setAdd] = useState(false)
    const[money,setMoney] = useState("")
    const handleClick = ()=>{
      axios.post("http://localhost:8080/api/v1/users/balance",{topUpAmount:money},{headers : {'Authorization': `Bearer ${token}`}}).then(
        (res)=>{
          alert(res.data.result.message)
          localStorage.setItem('balance',res.data.result.balance)
          setMoney("")
          setAdd(false)
        }
      ).catch(alert("some error"))
    }
    const token = localStorage.getItem('token')
  return (
    <div className="desktop-9">
      <img
        className="unsplash5dd7-l4a4uw-icon"
        alt=""
        src={background}
      />
      <div className="desktop-9-child" />
     
      <img className="desktop-9-inner" alt="" src={account} />
      <input className="search-bar-4" type="text" placeholder="Name" value={name} />
      <input className="search-bar-5" type="text" placeholder="Hostel Name" value={hostel}/>
      <input className="search-bar-6" type="text" placeholder="Email ID" value={email} />
      <input className="search-bar-7" type="text" placeholder="Phone NO" value={phone}/>
   
      <div className="rectangle-parent">
        <div className="group-child" />
        <div className="group-item" />  
        <a href="/sell" style={{'color':'white'}}><b className="sell">Sell</b></a>
      </div>
      <b className="your-profile">Your Profile</b>
      <div className="rectangle-group">
        <div className="group-item" />
       {(add)?(<><input className="add-wallet-balance" placeholder="Enter money to add" value={money} onChange={(e)=>{setMoney(e.target.value)}}></input><button onClick={()=>{handleClick()}} className="add-wallet-balance1">Submit</button></>):(<button className="add-wallet-balance" onClick={()=>{setAdd(true)}}> <b >Wallet Balance: {balance}</b></button>)} 
      </div>
      <div className="rectangle-container">
        <div className="group-item" />
        <b className="your-orders">Your Orders</b>
      </div>
    </div>
  );
};

export default Profile