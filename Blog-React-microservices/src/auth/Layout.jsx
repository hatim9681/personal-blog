import React from 'react'
import { Outlet } from "react-router-dom";
import image from "./logo.png"

export const LayoutAuth = () => {
  return (
    <div>
         <div className="lg:flex overflow-hidden">
      <div className="lg:w-1/2 xl:max-w-screen-sm ">
        <div className=" bg-gray-400 lg:bg-white flex justify-center lg:justify-start lg:px-12">
          <div className="cursor-pointer flex items-center">
            <div className="lg:hidden  cursor-pointer w-full flex justify-center items-center">
              <img
                src={image}
                alt="logo"
                style={{ width: "60%", height: "70%" }}
              />
            </div>
          </div>
        </div>
        <Outlet  />

      </div>
      <div className="hidden lg:flex items-center justify-center bg-gray-400 flex-1 h-screen ">
        <img src={image} style={{ width: "60%", height: "60%" }} />
      </div>
    </div>
    </div>
  )
}
