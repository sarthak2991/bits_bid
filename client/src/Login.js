import React from "react";

/* import { IconComponentNode } from "./IconComponentNode";
import { MobileAppStoreBadges } from "./MobileAppStoreBadges"; */
import bubble from "./assets/Bubble.png"
import girl from "./assets/Girl.jpeg"
import logo from "./assets/Logo-1.png"
const Login = () => {
  return ( <div className="desktop">
  <div className="overlap-wrapper">
    <div className="overlap">
      <div className="overlap-group">
        <div className="blob">
          <div className="div">
            <div className="group">
              <div className="overlap-group-2">
                <div className="ellipse" />
                <div className="ellipse-2" />
                <div className="ellipse-3" />
              </div>
            </div>
            <div className="ellipse-4" />
          </div>
        </div>
        <div className="overlap-group-wrapper">
          <div className="div">
            <div className="group">
              <div className="overlap-group-2">
                <div className="ellipse-5" />
                <div className="ellipse-6" />
                <div className="ellipse-7" />
              </div>
            </div>
            <div className="ellipse-4" />
          </div>
        </div>
        <img className="img" alt="Group" src={logo} />
        <div className="rectangle" />
        <div className="text-wrapper">BITSbids</div>
        <div className="text-wrapper-2">Welcome</div>
        <img
          className="portrait-of-happy"
          alt="Portrait of happy"
          src={girl}
        />
        <div className="rectangle-2" />
        <div className="rectangle-3" />
        {/* <MobileAppStoreBadges className="mobile-app-store" />
        <IconComponentNode className="mobile-app-store-badges" /> */}
        <div className="text-wrapper-3">About us</div>
        <div className="text-wrapper-4">Privacy policy</div>
        <div className="text-wrapper-5">to the Community!</div>
        <div className="text-wrapper-6">Login</div>
        <div className="text-wrapper-7">Sign-up</div>
        <div className="text-wrapper-8">New user?</div>
        <div className="text-wrapper-9">Welcome back!</div>
        <img className="three-glass-bubbles" alt="Three glass bubbles" src={bubble} />
      </div>
      <div className="text-wrapper-10">Contact Us</div>
    </div>
  </div>
</div>
    );
};

export default Login