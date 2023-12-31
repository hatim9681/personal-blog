import { createContext, useContext, useEffect, useState } from "react";
import PropTypes from "prop-types";

// Define PropTypes for the ContextProvider component
ContextProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

const StateContext = createContext({
    currentUser: {},
    userToken: null,
    setCurrentUser: {},
    setUserToken: () => {},
    logout:()=>{}
});

// Define the ContextProvider component
export function ContextProvider({ children }) {
    const [currentUser, setCurrentUser] = useState(() => {
        const storedUser = localStorage.getItem("currentUser");
        return storedUser ? JSON.parse(storedUser) : {};
    });
  
    
 
  
    useEffect(() => {
        localStorage.setItem("currentUser", JSON.stringify(currentUser));
    }, [currentUser]);


    const logout = () => {
      localStorage.clear();
    };
  

    const [size, setSize] = useState(null);
    const [listcommante, setlistcommante] = useState(null);
    
    const handleOpen = (value) => {setSize(value.size);setlistcommante(value.commentaire);};

    return (
        <StateContext.Provider
        value={{
          currentUser,logout,
          size,handleOpen,listcommante,setCurrentUser
        }}
        >
            {children}
        </StateContext.Provider>
    );
}

// Define the useStateContext hook
export function useStateContext() {
  return useContext(StateContext);
}