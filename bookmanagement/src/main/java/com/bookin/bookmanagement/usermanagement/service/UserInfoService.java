package com.bookin.bookmanagement.usermanagement.service;
import com.bookin.bookmanagement.address.AddressEntity;
import com.bookin.bookmanagement.address.AddressRepository;
import com.bookin.bookmanagement.usermanagement.dto.UserDto;
import com.bookin.bookmanagement.usermanagement.entity.UserInfo;
import com.bookin.bookmanagement.usermanagement.mapper.UserMapper;
import com.bookin.bookmanagement.usermanagement.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    UserInfoRepository repository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public Object addUser(UserDto userDto) {
       AddressEntity address = userDto.getAddress();
       AddressEntity addressSaved = addressRepository.save(address);
       if (userDto.getRoles() == null || userDto.getRoles().isEmpty()){
           userDto.setRoles("ROLE_USER");
        }
        UserInfo userInfo = UserInfo.builder().email(userDto.getEmail())
                .roles(userDto.getRoles())
                .name(userDto.getName())
                .country(userDto.getCountry())
                .password(encoder.encode(userDto.getPassword()))
                .address(addressSaved).build();
        return UserMapper.INSTANCE_USER.userToUserDto( repository.save(userInfo));
    }


}
