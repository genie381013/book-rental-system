package com.interview.tao.mapper;

import com.interview.tao.controller.BookResponse;
import com.interview.tao.controller.InventoryResponse;
import com.interview.tao.controller.UserResponse;
import com.interview.tao.entity.Book;
import com.interview.tao.entity.Inventory;
import com.interview.tao.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
public interface BookRentalMapper {

    @Mapping(target = "inventories", qualifiedByName = "toInventoryResponse")
    BookResponse toBookResponse(Book book);

    @Named("toInventoryResponse")
    @Mapping(target = "user", qualifiedByName = "toUserResponseWithoutInventories")
    @Mapping(target = "book", qualifiedByName = "toBookResponseWithoutInventories")
    InventoryResponse toInventoryResponse(Inventory inventory);

    @Mapping(target = "inventories", qualifiedByName = "toInventoryResponse")
    UserResponse toUserResponse(User user);

    @Named("toBookResponseWithoutInventories")
    @Mapping(target = "inventories", ignore = true)
    BookResponse toBookResponseWithoutInventories(Book book);

    @Named("toUserResponseWithoutInventories")
    @Mapping(target = "inventories", ignore = true)
    UserResponse toUserResponseWithoutInventories(User user);
}
