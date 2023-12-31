import axios from "axios";
import { Router } from "react-router-dom";



export const axiosClient = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL_Client}/api/`,
});

export const axiosLike = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL_Like}/api/`,
});

export const axiosCommantaire = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL_Commantaire}/api/`,
});

export const axiosBlog = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL_Blog}/api/`,
});


 axiosClient.interceptors.response.use(response => {
  return response;
 }, error => {
    if(error.response && error.response.status===401){
        Router.navigate('/')
    }
    throw error;
 })
 
 axiosLike.interceptors.response.use(response => {
  return response;
 }, error => {
    if(error.response && error.response.status===401){
        Router.navigate('/')
    }
    throw error;
 })
 
 axiosCommantaire.interceptors.response.use(response => {
  return response;
 }, error => {
    if(error.response && error.response.status===401){
        Router.navigate('/')
    }
    throw error;
 })
 
 axiosBlog.interceptors.response.use(response => {
  return response;
 }, error => {
    if(error.response && error.response.status===401){
        Router.navigate('/')
    }
    throw error;
 })



