import React from "react";
import  { useEffect, useState } from "react";
import { ImgWithBlurredCaption } from "../component/post";
import image from "./post.png";
import { useStateContext } from "../../context/context";
import { useNavigate } from "react-router-dom";
import { axiosBlog } from "../axios";
import { Input } from "@material-tailwind/react";
import { Textarea } from "@material-tailwind/react";
import { Button } from "@material-tailwind/react";

export const Myblogs = () => {
  const { currentUser } = useStateContext();
  const [selectedImageURL, setSelectedImageURL] = useState("");
  const [selectedImageFile, setSelectedImageFile] = useState(null);
  const [blogs , setblogs]=useState([])
  const [loading, setLoading] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const [formData, setFormData] = useState({
    titre: "",
    description: "",
    file: "",
    createur_id:currentUser?.id
  });

  const submitForm = async (event) => {
    setLoading(true)
    event.preventDefault();
    if (selectedImageFile) {
      formData.file= selectedImageFile;
    }  
    try {
        if (selectedImageURL) {
              const respo = await axiosBlog.post("blog/upload",formData, {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            });
            // console.log(respo.data);
            setLoading(false)
            getPost()
            setFormData({
                titre: "",
                description: "",
                file: "",
              });
              setSelectedImageURL('')
              
        }
      }catch (error) {
              
      }
  
  };

  

  const getPost = async () => {
    const respo = await axiosBlog.get("blog/all")
    setblogs(respo.data)
    console.log(respo.data)
  }
  const filteredBlogs = blogs.filter((blog) => blog.client.id === currentUser.id);

  useEffect(()=>{
    getPost()
  },[])


  return (
    <div className="w-full flex flex-col items-center gap-10 mt-12">
      <form onSubmit={submitForm} className="w-full gap-10 flex justify-center items-center flex-row bg-gray-100 p-4 rounded-xl">
        <div className="flex flex-col ">
          {selectedImageURL && (
            <div>
              <img
                className="w-24 h-24 rounded-xl mb-3"
                src={selectedImageURL}
                alt="Selected Image"
              />{" "}
            </div>
          )}
          <input
            color="black"
            className="flex h-10 max-w-[150px] w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50"
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
        </div>
        <div className="flex flex-col flex-1">
        <div>
            <div className="text-sm font-bold text-gray-700 tracking-wide">
              Titre
            </div>
            <Input
              className="w-full mb-5 text-lg py-2 border-b border-gray-300 focus:outline-none focus:border-black"
              id="nom"
              type="text"
              placeholder="Name"
              name='titre'
              onChange={handleInputChange}
              value={formData.titre}
              required
              autoFocus
            />
          </div>
          <div>
            <div className="text-sm font-bold text-gray-700 tracking-wide">
            description
            </div>
            <Textarea  
               name='description'
              onChange={handleInputChange}
              value={formData.description}
               label="Message" />
          </div>
        </div>
        <div className="h-full justify-center items-center">
                <Button type="submit" className="" disabled={loading}>
                    {loading ? "Posting..." : "Post Your Blog"}
                </Button>
        </div>
      </form>
        
      {filteredBlogs.map((blog, index) => (
        <ImgWithBlurredCaption key={index} blog={blog} />
      ))}
    </div>
  );
};
