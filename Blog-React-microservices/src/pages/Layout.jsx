import React from 'react'
import { Outlet } from "react-router-dom";
import  { ComplexNavbar } from '../component/navbar';
import { useStateContext } from '../../context/context';
import { Navigate, } from "react-router-dom";

export const LayoutHome = () => {
  const { currentUser  } = useStateContext();



  if (Object.keys(currentUser).length === 0) {
    
  return <Navigate to="/auth/signin" />;
}


  return (
    <div className='pt-16 p-6'>
      <ComplexNavbar />
        <Outlet  />
    </div>
  )
}
