import { Typography } from "@material-tailwind/react";
import {
  TrashIcon,
  HeartIcon,
  ChatBubbleLeftIcon,
} from "@heroicons/react/24/outline";
import { Button } from "@material-tailwind/react";
import { useStateContext } from "../../context/context";
import { Tooltip } from "@material-tailwind/react";
import { axiosBlog, axiosLike } from "../axios";

export function ImgWithBlurredCaption({ blog, getPost }) {
  const { handleOpen } = useStateContext();
  const { currentUser } = useStateContext();

  const jaime = async ({ test, id }) => {
    if (test === "yes") {
      const repo = await axiosLike.delete(`/like/delete/${id}`, {
        blog_id: blog.id,
        client_id: currentUser.id,
      });
    } else {
      const repo = await axiosLike.post("/like/save", {
        blog_id: blog.id,
        client_id: currentUser.id,
      });
    }

    getPost();
  };

  const delet = async (id) => {
    try {
      await axiosBlog.delete(`/blog/delete/${id}`);
      window.location.reload();
    } catch (error) {
      console.error("Error deleting blog:", error);
    }
  };

  return (
    <figure className="relative h-90 w-full lg:w-1/2 shadow-lg bg-gray-200 rounded-2xl">
      <div className="absolute right-2 top-2">
        {blog?.client?.id == currentUser?.id && (
          <Button onClick={()=>delet(blog.id)} className="absolute bg-gray-50 right-2 top-2">
            <TrashIcon width={30} className="text-black" height={30} />
          </Button>
        )}
      </div>
      <img
        className="h-full w-full rounded-xl object-cover object-center"
        src={blog.imageUrl}
        alt="nature image"
      />
      <figcaption className="absolute bottom-8 left-2/4 flex w-[calc(100%-4rem)] -translate-x-2/4 justify-between rounded-xl border border-white bg-white/75 py-4 px-6 shadow-lg shadow-black/5 saturate-200 backdrop-blur-sm">
        <div>
          <Typography variant="h5" color="blue-gray">
            {blog.description}
          </Typography>
          <Typography color="gray" className="mt-2 font-normal">
            {formatDate(blog.dateCreate)}
          </Typography>
        </div>
        <div className="flex flex-col">
          <div
            onClick={() => {
              const isLiked = blog?.jaimes?.some(
                (like) => like.client.id === currentUser?.id
              );
              jaime({
                test: isLiked ? "yes" : "no",
                id: isLiked
                  ? blog?.jaimes.find(
                      (like) => like?.client?.id === currentUser?.id
                    )?.id
                  : undefined,
              });
            }}
            className="flex flex-row justify-center items-center font-bold text-lg"
          >
            {blog?.jaimes?.length > 0 ? blog?.jaimes?.length : 0}
            <Typography variant="h5" color="blue-gray">
              
              {blog?.jaimes?.some(
                (like) => like.client.id === currentUser?.id
              ) ? (
                <div>
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="red"
                    className="w-6 h-6"
                  >
                    <path d="m11.645 20.91-.007-.003-.022-.012a15.247 15.247 0 0 1-.383-.218 25.18 25.18 0 0 1-4.244-3.17C4.688 15.36 2.25 12.174 2.25 8.25 2.25 5.322 4.714 3 7.688 3A5.5 5.5 0 0 1 12 5.052 5.5 5.5 0 0 1 16.313 3c2.973 0 5.437 2.322 5.437 5.25 0 3.925-2.438 7.111-4.739 9.256a25.175 25.175 0 0 1-4.244 3.17 15.247 15.247 0 0 1-.383.219l-.022.012-.007.004-.003.001a.752.752 0 0 1-.704 0l-.003-.001Z" />
                  </svg>
                </div>
              ) : (
                <HeartIcon width={30} className="text-red-400" height={30} />
              )}
            </Typography>
          </div>

          <div className="flex flex-row justify-center items-center font-bold text-lg">
          {blog?.commntaires?.length > 0 ? blog?.commntaires?.length : 0}            <Tooltip content="commentaire">
              <Typography
                onClick={() =>
                  handleOpen({ size: "md", commentaire: blog ,  })
                }
                variant="h5"
                color="blue-gray"
              >
                <ChatBubbleLeftIcon
                  width={30}
                  className="text-black"
                  height={30}
                />
              </Typography>
            </Tooltip>
          </div>
        </div>
      </figcaption>
    </figure>
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
