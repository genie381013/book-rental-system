import React, { createContext, useContext, useState } from 'react';

const UserContext = createContext(null);
export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const updateUser = (newUser) => {
        setUser(newUser);
    };

    return (
        <UserContext.Provider value={{ user, updateUser}}>
            {children}
        </UserContext.Provider>
    );
};

// Custom hook to use the UserContext
export const useUser = () => {
    return useContext(UserContext);
};
