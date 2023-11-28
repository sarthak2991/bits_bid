import "bootstrap/dist/css/bootstrap.min.css";
import { input, Button } from "react-bootstrap";
import "./index.css";
import background from './assets/unsplash_5DD7-L4A4Uw.png'
import account from './assets/Account.png'
const Profile = () => {
    const name = localStorage.getItem('name')
    const hostel = localStorage.getItem('hostel')
    const email = localStorage.getItem('email')
    const phone = localStorage.getItem('phone')
    const balance = localStorage.getItem('balance')
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
        <Button className="group-child" />
        <div className="group-item" />  
        <b className="sell">Sell</b>
      </div>
      <b className="your-profile">Your Profile</b>
      <Button className="rectangle-group">
        <div className="group-item" />
        <b className="wallet-balance">Wallet Balance: {balance}</b>
      </Button>
      <Button className="rectangle-container">
        <div className="group-item" />
        <b className="your-orders">Your Orders</b>
      </Button>
    </div>
  );
};

export default Profile