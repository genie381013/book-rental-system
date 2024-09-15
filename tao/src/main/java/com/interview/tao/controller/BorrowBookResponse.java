package com.interview.tao.controller;

import java.util.UUID;

public record BorrowBookResponse(UUID userId,
                                 String inventoryId) {}
