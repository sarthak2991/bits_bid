import axios from 'axios'
import React, { useState } from 'react'

const Details = () => {
    const [hostel,setHostel] = useState('')
    const [contactNo,setPhone] = useState('')
    const email = localStorage.getItem('email')
    const name = localStorage.getItem('name')
    const username = localStorage.getItem('username')
    const password = "1"
  return (
    <div>
        hostel name: <input placeholder='enter your hostel name' onChange={(e)=>{setHostel(e.target.value)}}/><br/>
        phone No : <input placeholder='enter your phone number' onChange={(e)=>{setPhone(e.target.value)}}/><br/>
        <button onClick={()=>{
            console.log(typeof(phone))
            axios.post("https://big-omc1.onrender.com/api/v1/users/sign_up",{email,username,contactNo,name,password,hostel}).then((res)=>{
             if(res.data.hasOwnProperty('result')){
                alert("sign up successfull")
                window.location.href = "/"
             }
             else{
                 alert(res.data.msg)
             }
        })}}>Submit</button>
    </div>
  )
}

export default Details