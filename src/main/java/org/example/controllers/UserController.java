package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.dto.ResponseDto;
import org.example.dto.UserDto;
import org.example.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Log4j2
public class UserController {

    private final IUserService iUserService;

    //Get path /api/users/getuser/all
    @GetMapping("/getuser/all")
    public List<?> getListUsers() {

        ResponseDto responseDto = new ResponseDto();
        List<UserDto> allUsers = iUserService.getAllUsers();
        if (allUsers.isEmpty()){
            responseDto.setMessage("Users not found");
            return Collections.singletonList(ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto));
        }else{
            return allUsers;
        }
    }

    //Post path /api/users/adduser
    //Body request = {
    //    "numberDocumentUser":123345,
    //    "emailUser":"usamo4489@gmail.com",
    //    "namesUser":"kevin andres",
    //    "lastNamesUser":"usama"
    //}

    @PostMapping("/adduser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Si hay errores de validación, devolver mensajes de error
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        try {
            iUserService.createUser(userDto);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("User Created");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception ex){
            log.error(ex);
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
