package com.interview.tao.controller;

import java.util.List;
import java.util.UUID;

public record BookResponse (UUID id,
                            String title,
                            String author,
                            String image,
                            List<InventoryResponse> inventories){
}
