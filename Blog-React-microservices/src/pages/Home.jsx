import React, { useEffect, useState } from 'react'
import { ImgWithBlurredCaption } from '../component/post'
import image from "./post.png"
import { useStateContext } from '../../context/context';
import {useNavigate} from 'react-router-dom';
import { axiosBlog } from '../axios';

export const Home = () => {
  const { currentUser  } = useStateContext();
  let navigate = useNavigate();
  const [blogs , setblogs]=useState([])


  const getPost = async () => {
    const respo = await axiosBlog.get("blog/all")
    setblogs(respo.data)
  }
  useEffect(()=>{
    getPost()
  },[])


    
  if(!currentUser.email){
    navigate("/auth/signin")
  }
  return (
    <div className='w-full flex flex-col items-center gap-10 mt-12'>
      {blogs.map((blog , index)=>(
      <ImgWithBlurredCaption key={index} blog={blog} getPost={getPost}/>
      ))}
    </div>
  )
}
