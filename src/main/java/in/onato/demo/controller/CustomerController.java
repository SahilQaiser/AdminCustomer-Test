package in.onato.demo.controller;

import in.onato.demo.dto.Mapper;
import in.onato.demo.dto.UpdateUserRequest;
import in.onato.demo.dto.UserDTO;
import in.onato.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/me")
    public UserDTO getCustomerDetails(Authentication authentication) {
        String username = authentication.getName();
        return mapper.toDTO(userService.findByUsername(username));
    }

    @PutMapping("/me")
    public void updateCustomer(@RequestBody UpdateUserRequest request, Authentication authentication) {
        UpdateUserRequest updateCustomerRequest = new UpdateUserRequest();
        updateCustomerRequest.setAddress(request.getAddress());
        updateCustomerRequest.setPassword(request.getPassword());
        userService.updateCustomer(authentication.getName(), updateCustomerRequest);
    }
}
