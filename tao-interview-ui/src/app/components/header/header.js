import React, {useState} from "react";
import './header.css'
import LoginPopup from "../login/loginPopup";
import {useUser} from "../../UserContext";
import {useNavigate} from "react-router-dom";

export default function Header() {
    const [userName] = useState(useUser().user?.username);
    const [openPopup] = useState(false);

    const navigator = useNavigate();

    const handleReturnToLandingPage = () => {
        navigator("/");
    }

    return (
        <header className="header">
            <div className="header-left">
                <span className="header-title" onClick={handleReturnToLandingPage}>𝘽𝙤𝙤𝙠 𝙍𝙚𝙣𝙩𝙖𝙡</span>
            </div>
            <div className="header-right">
                <LoginPopup userName={userName} open={openPopup}/>
            </div>
        </header>
    )

}