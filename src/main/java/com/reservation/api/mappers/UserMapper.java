package com.reservation.api.mappers;

import com.reservation.api.entities.UserEntity;
import com.reservation.api.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(User model);
}
