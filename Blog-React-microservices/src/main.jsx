import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { NextUIProvider } from "@nextui-org/react";
import { RouterProvider } from "react-router-dom";
import { ContextProvider } from "../context/context.jsx";
import router from "./routes.jsx";
import { App } from "./app.jsx";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ContextProvider>
      <NextUIProvider>
        <App />
      </NextUIProvider>
    </ContextProvider>
  </React.StrictMode>
);
