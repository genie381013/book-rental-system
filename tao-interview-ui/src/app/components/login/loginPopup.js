import {useEffect, useRef, useState} from "react";
import "./loginPopup.css";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useUser} from "../../UserContext";

export default function LoginPopup() {
    const [showPopup, setShowPopup] = useState(false);
    const popupRef = useRef(null);
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const userContext = useUser();
    const user = userContext.user;
    const togglePopup = () => {
        setShowPopup(!showPopup);
    };

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (popupRef.current && !popupRef.current.contains(event.target)) {
                setShowPopup(false);
            }
        };
        document.addEventListener('mousedown', handleClickOutside);
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };
    }, [popupRef, user]);

    const handleProfileClick = () => {
        navigate('/profile');
    };

    const handleLogin = () => {
        const request = {username: username, password: password};
        axios.postForm("/api/login", request)
            .then(response => {
                if (response.status === 200)
                {
                    axios.post("/api/user/login", request)
                        .then(res => { userContext.updateUser(res.data); });
                }
            });
    }

    const handleLogout = () => {
        axios.post("/api/logout")
            .then(() => {
                userContext.updateUser(null);
            });
        navigate('/');
    }

    const handlePopupContent = () => {
        return user ? (
            <div className="profile-popup" ref={popupRef}>
                <p>Hi {user.username}</p>
                <button className="profile-button" onClick={handleProfileClick}>
                    Profile
                </button>
                <button className="sign-button" onClick={handleLogout}>Sign Out</button>
            </div>
        ) : (
            <div className="profile-popup" ref={popupRef}>
                <input type="text" className="form-control" name="username" placeholder="username" value={username}
                       onChange={(e) => setUsername(e.target.value)} required/>
                <input type="password" className="form-control" name="password" placeholder="password" value={password}
                       onChange={(e) => setPassword(e.target.value)} required/>
                <button type="submit" className="sign-button" onClick={handleLogin}>Sign In</button>
            </div>
        );
    }

    return (
        <div className="profile-container">
      <span className="profile-name" onClick={togglePopup}>
        {user?.username ? "Profile" : "Sign in"}
      </span>
            {showPopup && handlePopupContent()}
        </div>
    );
}