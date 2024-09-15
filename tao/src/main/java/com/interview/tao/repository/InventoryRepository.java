package com.interview.tao.repository;

import com.interview.tao.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Optional<Inventory> findById(UUID id);

    Optional<Inventory> findByBook_id(UUID bookId);

}
