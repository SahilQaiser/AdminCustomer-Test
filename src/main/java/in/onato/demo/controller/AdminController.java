package in.onato.demo.controller;

import in.onato.demo.dto.CreateUserRequest;
import in.onato.demo.dto.Mapper;
import in.onato.demo.dto.UpdateUserRequest;
import in.onato.demo.dto.UserDTO;
import in.onato.demo.entity.User;
import in.onato.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    private Mapper mapper;

    @PostMapping("/customer")
    public void createCustomer(@RequestBody CreateUserRequest request) {
        userService.createCustomer(request);
    }

    @PostMapping("/admin")
    public void createAdmin(@RequestBody CreateUserRequest request) {
        userService.createAdmin(request);
    }

    @GetMapping("/customers")
    public List<UserDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        userService.deleteCustomer(id);
    }

    @PutMapping("/me")
    public void updateAdmin(@RequestBody UpdateUserRequest request, Authentication authentication) {
        userService.updateCustomer(authentication.getName(), request);
    }
}
