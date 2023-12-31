import React, { useEffect, useState } from "react";
import {
  Button,
  Dialog,
  DialogHeader,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import { useStateContext } from "../../context/context";
import { Avatar } from "@material-tailwind/react";
import { Input } from "@material-tailwind/react";
import { PaperAirplaneIcon } from "@heroicons/react/24/outline";
import { axiosCommantaire } from "../axios";
import {
  TrashIcon,
  HeartIcon,
  ChatBubbleLeftIcon,
} from "@heroicons/react/24/outline";

export function DialogSizes() {
  const { currentUser, size, handleOpen, listcommante } = useStateContext();
  const [commentInput, setCommentInput] = useState("");
  const [comment, setListcommante] = useState([]);


  const handleCommentInputChange = (e) => {
    setCommentInput(e.target.value);
  };

  const getBlog = async (id)=>{
      const respo = await axiosCommantaire.get(`/commentaire/blog_id/${id}`)
      setListcommante(respo.data)
      console.log(respo.data)
    
  }

  useEffect(() => {
    if(listcommante){
      getBlog(listcommante.id)

    }
  }, [listcommante]);

  const sendComment = async () => {
    try {
      const response = await axiosCommantaire.post(`/commentaire/save`, {
        description: commentInput,
        blog_id: listcommante.id,
        client_id: currentUser.id,
      });
      setCommentInput("");
      getBlog(listcommante.id)
    } catch (error) {
      console.error("Error sending comment:", error);
    }
  };



  return (
    <>
      <Dialog
        open={
          size === "xs" ||
          size === "sm" ||
          size === "md" ||
          size === "lg" ||
          size === "xl" ||
          size === "xxl"
        }
        size={size || "xl"}
        handler={handleOpen}
      >
        <DialogHeader className="flex flex-row justify-between">
          <div>Commentaire:</div>
          <Button
            variant="text"
            color="red"
            onClick={() => handleOpen({ size: null, commentaire: null })}
            className="mr-1"
          >
            <span>Close</span>
          </Button>
        </DialogHeader>
        <DialogBody className="flex flex-col gap-2 overflow-auto ">
          {comment?.map((com, index) => (
            <div className="flex flex-row">
              <div key={index} className="flex flex-1 flex-row gap-2">
                <Avatar src={com?.client?.image} alt="avatar" />
                <div className="flex flex-col">
                  <h1 className="text-black font-bold text-md">
                    {com.client.nom}
                  </h1>
                  <h1 className="text-gray-400 text-sm">{com.description}</h1>
                </div>
              </div>
              <div className="flex flex-col justify-center items-center">
               {formatDate(com.dateCreate)}
               {com?.client?.id == currentUser?.id && (
                  <TrashIcon onClick={()=>delet(com.id)} width={30} className="text-black" height={30} />
              )}
              </div>
            </div>
          ))}
        </DialogBody>
        <DialogFooter>
          <div className="w-full flex flex-row justify-center items-center">
            <Input
              label="write your idea about the blog"
              value={commentInput}
              onChange={handleCommentInputChange}
            />
            <Button className="bg-transparent " onClick={sendComment}>
              <PaperAirplaneIcon
                className="text-red-400"
                width={25}
                height={25}
              />
            </Button>
          </div>
        </DialogFooter>
      </Dialog>
    </>
  );
}


export const formatDate = (dateString) => {
  const date = new Date(dateString);
  const today = new Date();

  const options = { hour: "numeric", minute: "numeric" };
  const timeFormat = new Intl.DateTimeFormat("en-US", options).format(date);

  if (isToday(date)) {
    return `Today at ${timeFormat}`;
  } else if (isYesterday(date)) {
    return `Yesterday at ${timeFormat}`;
  } else {
    // Format for other days
    const dateFormat = new Intl.DateTimeFormat("en-US", {
      month: "short",
      day: "numeric",
    }).format(date);

    return `${dateFormat} at ${timeFormat}`;
  }
};

const isToday = (someDate) => {
  const today = new Date();
  return (
    someDate.getDate() === today.getDate() &&
    someDate.getMonth() === today.getMonth() &&
    someDate.getFullYear() === today.getFullYear()
  );
};

const isYesterday = (someDate) => {
  const yesterday = new Date();
  yesterday.setDate(yesterday.getDate() - 1);

  return (
    someDate.getDate() === yesterday.getDate() &&
    someDate.getMonth() === yesterday.getMonth() &&
    someDate.getFullYear() === yesterday.getFullYear()
  );
};