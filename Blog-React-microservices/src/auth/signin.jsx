import React, { useState } from 'react'
import { axiosClient } from '../axios';
import { useStateContext } from '../../context/context';
import { Navigate, } from "react-router-dom";

export const Signin = () => {
  const { currentUser , setCurrentUser } = useStateContext();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
const  [message , setmessage]=useState(false)

  

if (Object.keys(currentUser).length !== 0) {
  return <Navigate to="/" />;
}


  const submitForm = async (event) => {
    event.preventDefault();
    const respo = await axiosClient.post("client/auth",{
      email: email,
      password: password
    })
    // console.log(respo.data.image)
    setCurrentUser({
      id:respo.data.id,
      name: respo.data.nom,
      email: respo.data.email,
      telephone: respo.data.telephone,
      image: respo.data.image 
    })
    if(respo.data.email){
      return <Navigate to="/auth/signin" />;
    }else{
      setmessage(true)
    }
  };

 


  return (
    <div className="mt-24 px-12 sm:px-24 md:px-48 lg:px-12 lg:mt-16 xl:px-24 xl:max-w-2xl">
    <h2
      className="text-center text-4xl text-black font-display font-semibold lg:text-left xl:text-5xl
        xl:text-bold"
    >
      Log in
    </h2>
    <div className="mt-12">
      <form onSubmit={submitForm}>

        <div>
          {message &&
              <p>Email or Password inCorrect</p>
          }
          {message}
          <div className="text-sm font-bold text-gray-700 tracking-wide">
            Email Address
          </div>
          <input
            className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-[black"
            placeholder="mike@gmail.com"
            id="email"
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            required
            autoFocus
          />
        </div>
        <div className="mt-8">
          <div className="flex justify-between items-center">
            <div className="text-sm font-bold text-gray-700 tracking-wide">
              Password
            </div>
            <div>
              <a href="/auth/forget-password"
                className="text-xs font-display font-semibold text-black hover:text-indigo-800
                            cursor-pointer"
              >
                Forgot Password?
              </a>
            </div>
          </div>
          <input
            className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-[black"
            placeholder="Enter your password"
            id="password"
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            required
            autoComplete="current-password"
          />
        </div>
        <div className='w-full  mt-8 flex justify-center items-center '>
        <button type='submit' className='bg-black text-white py-3 px-6 rounded-md text-center'>Sign In</button>
        </div>
      </form>
      <div className="mt-12 text-sm font-display font-semibold text-gray-700 text-center">
        Don't have an account ?{" "}
        <a
          className="cursor-pointer text-black hover:text-indigo-800"
          href="/auth/signup"
        >
          Sign up
        </a>
      </div>
    </div>
  </div>
  )
}
