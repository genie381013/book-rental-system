import React from 'react';
import Header from "./components/header/header";
import Landing from "./pages/landing/landing";
import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import DetailPage from "./pages/detail/detail";
import ProfilePage from "./pages/profile/profile";
import {UserProvider} from "./UserContext";

export default function App() {
    return (
        <UserProvider>
        <Router>
            <Header />
            <Routes>
                <Route exact path="/" element={<Landing/>} />
                <Route path="/details" element={<DetailPage />} />
                <Route path="/profile" element={<ProfilePage/>} />
            </Routes>
        </Router>
        </UserProvider>
    );
}