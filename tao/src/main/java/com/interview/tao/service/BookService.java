package com.interview.tao.service;

import com.interview.tao.common.BookNotFoundException;
import com.interview.tao.common.ConflictException;
import com.interview.tao.common.UserNotFoundException;
import com.interview.tao.controller.BookResponse;
import com.interview.tao.entity.Book;
import com.interview.tao.entity.Inventory;
import com.interview.tao.entity.User;
import com.interview.tao.mapper.BookRentalMapper;
import com.interview.tao.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final InventoryService inventoryService;

    private final UserService userService;

    private final BookRentalMapper bookRentalMapper;

    BookService(BookRepository bookRepository,
                InventoryService inventoryService,
                UserService userService,
                BookRentalMapper bookRentalMapper) {
        this.bookRepository = bookRepository;
        this.inventoryService = inventoryService;
        this.userService = userService;
        this.bookRentalMapper = bookRentalMapper;
    }

    public List<BookResponse> getBooks(Integer offset, Integer limit)
    {
        return bookRepository.findAll(PageRequest.of(offset, limit)).getContent()
                .stream()
                .map(bookRentalMapper::toBookResponse)
                .toList();
    }

    public BookResponse getBookById(UUID uuid) throws BookNotFoundException {
        Book book = getBook(uuid);
        return bookRentalMapper.toBookResponse(book);
    }

    public void returnBook(UUID userId, UUID inventoryId) throws BookNotFoundException, ConflictException {
        Inventory inventory = inventoryService.getInventory(inventoryId);
        if (!userId.equals(inventory.getUser().getId()))
        {
            throw new ConflictException("The book can't be returned by another user");
        }
        inventoryService.returnBookUpdateInventory(inventory);
    }

    public void borrowBook(UUID userId, UUID inventoryId)
            throws UserNotFoundException, ConflictException, BookNotFoundException {
        User user = userService.getUser(userId);
        inventoryService.borrowBookUpdateInventory(user, inventoryId);
    }

    private Book getBook(UUID uuid) throws BookNotFoundException {
        return bookRepository.findById(uuid)
                .orElseThrow(() -> new BookNotFoundException("The book is not found"));
    }

}
