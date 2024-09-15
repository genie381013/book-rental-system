import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, CardImg, CardText, Modal, Table} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import {useSearchParams} from "react-router-dom";
import "./detail.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {useUser} from "../../UserContext";
import ErrorModal from "../../components/modal/errorModal";

export default function DetailPage() {

    const [bookId, setBookId] = useState('');
    const [book, setBook] = useState({});
    const [searchParams] = useSearchParams();
    const [showModal, setShowModal] = useState(false);
    const [modalAction, setModalAction] = useState('');
    const [selectedInventoryId, setSelectedInventoryId] = useState(null);
    const user = useUser().user;

    // error modal
    const [showError, setShowError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        setBookId(searchParams.get('bookId'));
        if (!!bookId)
        {
            handleGetBook();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [bookId, showModal])

    const handleClose = () => setShowModal(false);
    const handleShow = (action, inventoryId) => {
        setModalAction(action); // Set action to 'Borrow' or 'Return'
        setShowModal(true);
        setSelectedInventoryId(inventoryId);
    };

    const handleCloseError = () => {
        setShowError(false);
    };

    const handleGetBook = () =>
    {
        axios.get(`/api/books/${bookId}`)
            .then(response => setBook(response.data));
    }

    const handleModalAction = () => {
        if (modalAction === 'Return') {
            handleReturnBook();
        } else {
            handleBorrowBook();
        }
    }
    const handleBorrowBook = () =>
    {
        const request = {userId: user?.id, inventoryId: selectedInventoryId}
        axios.put("/api/books/borrow", request)
            .then(() => handleClose())
            .catch(response => {
                setErrorMessage(response.response?.data.message || "Please log in.");
                setShowError(true);
                handleClose();
            });
    }

    const handleReturnBook = () =>
    {
        const request = {userId: user?.id, inventoryId: selectedInventoryId}
        axios.put("/api/books/return", request)
            .then(() => handleClose())
            .catch(response => {
                setErrorMessage(response.response?.data.message || "Please log in.");
                setShowError(true);
                handleClose();
            });
    }

    return (
        <div className="details">
            <Card className="bookImage">
                <CardImg src={book.image} alt={book.title}/>
                <CardText> {book.title} </CardText>
                <div className="author-label"> {book.author} </div>
            </Card>
            <div className="table-container">
                <Table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>USER</th>
                        <th>DATE</th>
                        <th>ACTION</th>
                    </tr>
                    </thead>
                    <tbody>
                    {book.inventories?.map(inventory => (
                        <tr key={inventory.id}>
                            <td>{inventory.id}</td>
                            <td>{inventory.user?.username}</td>
                            <td>{inventory.loanDate || ""}</td>
                            <td><Button onClick={() => handleShow(inventory.user?.id ? 'Return' : 'Borrow', inventory.id)}>
                                {inventory.user?.id ? "RETURN" : "BORROW"}</Button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>

                <Modal show={showModal} onHide={handleClose} centered>
                    <Modal.Header closeButton>
                        <Modal.Title>Action</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        Would you like to {modalAction.toLowerCase()} this book?
                    </Modal.Body>
                    <Modal.Footer>
                        <Button
                            variant="primary"
                            onClick={() => handleModalAction()}
                            style={{ width: '100%', backgroundColor: '#002B6B', border: 'none' }}
                        >
                            Continue
                        </Button>
                    </Modal.Footer>
                </Modal>
                <ErrorModal show={showError} handleClose={handleCloseError} errorMessage={errorMessage}/>
            </div>
        </div>
    );
}