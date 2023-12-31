import { createBrowserRouter } from "react-router-dom";
import { LayoutAuth } from "./auth/Layout";
import { Signin } from "./auth/signin";
import { Signup } from "./auth/signup";
import { Home } from "./pages/Home";
import { LayoutHome } from "./pages/Layout";
import { Myblogs } from "./pages/myblogs";

const router = createBrowserRouter([
    {
        path: "/",
        element: <LayoutHome />,
        children: [
            {
                path: "",
                element: <Home />,
            },
            {
                path: "/myblogs",
                element: <Myblogs />,
            },
            

        ]
    },
    {
        path: "/auth",
        element: <LayoutAuth />,
        children: [
            {
                path: "signin",
                element: <Signin />,
            },
            {
                path: "signup",
                element: <Signup />,
            },

            

        ]
    },
]);

export default router;