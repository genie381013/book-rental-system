import "./profile.css";
import {useUser} from "../../UserContext";
import {useEffect, useState} from "react";
import axios from "axios";

export default function ProfilePage()
{
    const userContext = useUser();
    const [user, updateUser] = useState(userContext.user);

    useEffect(() => {
        loadData();
        // eslint-disable-next-line
    }, []);

    const loadData = () => {
        axios.get(`/api/user/${user.id}`)
            .then(response => updateUser(response.data))
    }

    return (
        <div className="profile-page">
            <section className="profile-info">
                <h2>Profile</h2>
                <div className="profile-detail">
                    <p><strong>Name</strong></p>
                    <p>{user.username}</p>
                </div>
                <div className="profile-detail">
                    <p><strong>Role</strong></p>
                    <p>{user.role}</p>
                </div>
            </section>

            <section className="borrowed-books">
                <h2>Borrowed Books</h2>
                <div className="book-grid">
                    {user.inventories?.map((inventory) => (
                        <div key={inventory.id} className="book-card">
                            <img src={inventory.book.image} alt={inventory.book.title}/>
                            <p>{inventory.book.title}</p>
                        </div>
                    ))}
                </div>
            </section>
        </div>
    );
}