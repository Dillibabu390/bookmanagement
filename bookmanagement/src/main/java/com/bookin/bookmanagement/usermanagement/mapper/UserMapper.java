package com.bookin.bookmanagement.usermanagement.mapper;
import com.bookin.bookmanagement.usermanagement.dto.UserDto;
import com.bookin.bookmanagement.usermanagement.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE_USER = Mappers.getMapper(UserMapper.class);


    UserDto userToUserDto(UserInfo userInfo);

    UserInfo userDtoToUser(UserDto userDto);


}
