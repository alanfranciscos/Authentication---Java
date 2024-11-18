package com.lds.authentication.controller.user;

import com.lds.authentication.domain.UserModel;
import com.lds.authentication.port.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserModel>> getEntities() {
        List<UserModel> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<UserModel> getEntityById(@PathVariable final int id) {
        UserModel user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<UserModel> getEntityByEmail(@PathVariable final String email) {
        UserModel user = userService.findByEmail(email);
        return ResponseEntity.ok().body(user);
    }
}
