import { Input } from "@material-tailwind/react";
import React, { useState } from "react";
import { axiosClient } from "../axios";

export const Signup = () => {

  const [formData, setFormData] = useState({
    nom: "",
    email: "",
    telephone: "",
    password: "",
    confirm_password: "",
    file: "",
  });


  const [selectedImageURL, setSelectedImageURL] = useState("");
  const [selectedImageFile, setSelectedImageFile] = useState(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const submitForm = async (event) => {
    event.preventDefault();

    if (selectedImageFile) {
      formData.file= selectedImageFile;
    }
  
    try {
      if (selectedImageURL) {
        console.log(formData)
        try {
          const response = await axiosClient.post("client/register1", formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
          console.log(response);
          setCurrentUser({
            name: response.data.name,
            email: response.data.email,
            telephone: response.data.telephone,
            image:response.date.Input
          });
        } catch (error) {
        }
      }
     
      if (respo.data.email) {
        navigate("/");
      } else {
        setmessage(true);
      }
      console.log();
    } catch (error) {
    }
  };

  return (
    <div className="mt-10 px-12 sm:px-24 md:px-48 lg:px-12 lg:mt-16 xl:px-24 xl:max-w-2xl">
      <h2
        className="text-center text-4xl text-black font-display font-semibold lg:text-left xl:text-5xl
        xl:text-bold"
      >
        Sign up
      </h2>
      <div className="mt-12">
        <form onSubmit={submitForm}>
          {selectedImageURL && (
            <div>
      <img className="w-24 h-24 rounded-full mb-10" src={selectedImageURL} alt="Selected Image" />            </div>
          )}
        <Input
        color="black"
        className="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
        name="file"
        label="Image"
        type="file"
        onChange={(e) => {
          const file = e.target.files?.[0];
          if (file) {
            const imageURL = URL.createObjectURL(file);
            setSelectedImageURL(imageURL);
            setSelectedImageFile(file);
            console.log(imageURL);

          }
        }}
        value={formData.name}
      />

          <div>
            <div className="text-sm font-bold text-gray-700 tracking-wide">
              Name
            </div>
            <input
              className="w-full mb-5 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              id="nom"
              type="text"
              placeholder="Name"
              name='nom'
              onChange={handleInputChange}
              value={formData.nom}
              required
              autoFocus
            />
          </div>
          <div>
            <div className="text-sm font-bold text-gray-700 tracking-wide">
              Telephone
            </div>
            <input
              className="w-full mb-5 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              id="tele"
              type="text"
              name='telephone'
              placeholder="Telephone"
              onChange={handleInputChange}
              value={formData.telephone}
              required
              autoFocus
            />
          </div>
          <div>
            <div className="text-sm  font-bold text-gray-700 tracking-wide">
              Email Address
            </div>
            <input
              className="w-full mb-5  text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              type="email"
              placeholder="mike@gmail.com"
              id="email"
              name='email'
              onChange={handleInputChange}
              value={formData.email}
              required
            />
          </div>
          <div className="mt-3">
            <div className="flex justify-between items-center">
              <div className="text-sm font-bold text-gray-700 tracking-wide">
                Password
              </div>
            </div>
            <input
              className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              type="password"
              placeholder="Enter your password"
              id="password"
              name='password'
              onChange={handleInputChange}
              value={formData.password}
              required
              autoComplete="new-password"
            />
          </div>
          <div className="mt-8">
            <div className="flex justify-between items-center">
              <div className="text-sm font-bold text-gray-700 tracking-wide">
                Confirm Password
              </div>
            </div>
            <input
              className="w-full text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              id="confirm_password"
              type="password"
              name='confirm_password'
              onChange={handleInputChange}
              value={formData.confirm_password}
      
            required
            />
          </div>
          <div className='w-full  mt-8 flex justify-center items-center '>
        <button type='submit' className='bg-black text-white py-3 px-6 rounded-md text-center'>Sign Up</button>
        </div>
        </form>
        <div className="mt-8 mb-3 text-sm font-display font-semibold text-gray-700 text-center">
          Don't have an account ?
          <a
            className="cursor-pointer text-black hover:text-indigo-800"
            href="/auth/signin"
          >
            Sign In
          </a>
        </div>
      </div>
    </div>
  );
};
