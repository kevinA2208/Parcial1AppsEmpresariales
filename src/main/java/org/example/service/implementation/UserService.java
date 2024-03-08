package org.example.service.implementation;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.jpa.entities.UserEntity;
import org.example.jpa.repositories.UserRepository;
import org.example.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setIdUser(user.getIdUser());
                    userDto.setNumberDocumentUser(user.getNumberDocumentUser());
                    userDto.setEmailUser(user.getEmailUser());
                    userDto.setNamesUser(user.getNamesUser());
                    userDto.setLastNamesUser(user.getLastNamesUser());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createUser(@Valid UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setNumberDocumentUser(userDto.getNumberDocumentUser());
        userEntity.setEmailUser(userDto.getEmailUser());
        userEntity.setNamesUser(userDto.getNamesUser());
        userEntity.setLastNamesUser(userDto.getLastNamesUser());

        userRepository.save(userEntity);
    }
}
