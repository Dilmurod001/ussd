package uz.dilmurod.appussd.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dilmurod.appussd.entity.User;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.AuthRegisterDTO;
import uz.dilmurod.appussd.payload.LoginDto;
import uz.dilmurod.appussd.repository.UserRepository;
import uz.dilmurod.appussd.service.AuthService;

import java.util.List;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody AuthRegisterDTO authRegisterDTO) throws NotFoundException {
        ApiResponse apiResponse = authService.registerUser(authRegisterDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = authService.loginUser(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    @GetMapping
    public HttpEntity<?> all(@RequestBody LoginDto loginDto) {
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
