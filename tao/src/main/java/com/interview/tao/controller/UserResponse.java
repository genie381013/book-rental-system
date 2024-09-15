package com.interview.tao.controller;

import java.util.List;
import java.util.UUID;

public record UserResponse(UUID id, String username,
                           String role, List<InventoryResponse> inventories) {
}
