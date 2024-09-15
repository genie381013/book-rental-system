package com.interview.tao.controller;

import com.interview.tao.common.BookNotFoundException;
import com.interview.tao.common.ConflictException;
import com.interview.tao.common.UserNotFoundException;
import com.interview.tao.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @PutMapping("/return")
    public ResponseEntity<Void> returnABook(@RequestBody BorrowBookRequest borrowBookRequest) throws BookNotFoundException, ConflictException {
        bookService.returnBook(borrowBookRequest.userId(), borrowBookRequest.inventoryId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/borrow")
    public ResponseEntity<Void> borrowABook(@RequestBody BorrowBookRequest borrowBookRequest)
            throws BookNotFoundException, UserNotFoundException, ConflictException {
        bookService.borrowBook(borrowBookRequest.userId(), borrowBookRequest.inventoryId());
        return ResponseEntity.accepted().build();
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAllBooks(@RequestParam Integer offset, @RequestParam Integer limit)
    {
        List<BookResponse> books = bookService.getBooks(offset, limit);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") @NotNull UUID bookId) throws BookNotFoundException {
        BookResponse response = bookService.getBookById(bookId);
        return ResponseEntity.ok(response);
    }

}
