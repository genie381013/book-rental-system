package com.interview.tao.controller;

import java.time.LocalDateTime;
import java.util.UUID;

public record InventoryResponse(UUID id,
                                LocalDateTime loanDate,
                                BookResponse book,
                                UserResponse user){
}
