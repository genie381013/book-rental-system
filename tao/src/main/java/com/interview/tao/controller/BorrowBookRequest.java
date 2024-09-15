package com.interview.tao.controller;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record BorrowBookRequest(UUID userId,
                                UUID inventoryId) {}
