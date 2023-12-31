import React, { useContext } from "react";
import { RouterProvider } from "react-router-dom";
import router from "./routes";
import { DialogSizes } from "./component/drawer";

export const App = () => {
  return (
    <main
      className={` text-foreground bg-background h-screen `}
    >
      <RouterProvider router={router} />
      <DialogSizes />

    </main>
  );
};
