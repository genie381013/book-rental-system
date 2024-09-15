import React, {useEffect, useState} from "react";
import "./landing.css";
import axios from "axios";
import Card from "react-bootstrap/Card";
import {CardGroup, CardImg} from "react-bootstrap";
import {useNavigate} from "react-router-dom";

export default function Landing() {
    const [books, setBooks] = useState([]);
    const [offset] = useState(0);
    const [limit] = useState(10);

    const navigate = useNavigate();

    useEffect(() => {
        handleFetchBooks();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [offset, limit]);

    function handleFetchBooks()
    {
        axios.get(`/api/books?offset=${offset}&limit=${limit}`)
            .then(response => {
                setBooks(response.data);
            });
    }

   return (
        <div>
            <p className="desc"> Books you might like </p>
            <CardGroup className="card-group">
                {books.map(book => (
                    <Card className="bookImages" key={book.id}  onClick={() => navigate(`/details?bookId=${book.id}`)}>
                        <CardImg src={book.image} alt={book.title}/>
                    </Card>
                ))}
            </CardGroup>
        </div>
   );
}