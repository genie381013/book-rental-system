package com.interview.tao.service;

import com.interview.tao.common.BookNotFoundException;
import com.interview.tao.common.ConflictException;
import com.interview.tao.entity.Inventory;
import com.interview.tao.entity.User;
import com.interview.tao.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventory(UUID inventoryId) throws BookNotFoundException {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new BookNotFoundException("The book is not found"));
    }

    public void borrowBookUpdateInventory(User user, UUID inventoryId) throws BookNotFoundException, ConflictException {
        Inventory inventory = getInventory(inventoryId);
        if (Objects.nonNull(inventory.getUser())) {
            throw new ConflictException("The book is already borrowed");
        }
        inventory.setUser(user);
        inventory.setLoanDate(LocalDateTime.now());
    }

    public void returnBookUpdateInventory(Inventory inventory) {
        inventory.setUser(null);
        inventory.setLoanDate(null);
    }
}
