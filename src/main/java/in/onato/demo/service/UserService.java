package in.onato.demo.service;

import in.onato.demo.dto.Mapper;
import in.onato.demo.dto.UserDTO;
import in.onato.demo.entity.Role;
import in.onato.demo.entity.User;
import in.onato.demo.dto.CreateUserRequest;
import in.onato.demo.dto.UpdateUserRequest;
import in.onato.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import in.onato.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createAdmin(User admin) {
        return userRepository.save(admin);
    }

    public List<UserDTO> getAllCustomers() {
        return userRepository.findAll().stream().filter(customer -> customer.hasRole(Role.CUSTOMER)).
                map(mapper::toDTO).collect(Collectors.toList());
    }

    public void deleteCustomer(Integer customerId) {
        userRepository.deleteById(customerId);
    }

    public void createCustomer(CreateUserRequest createUserRequest) {
        User customer = new User(createUserRequest.getUsername(), passwordEncoder.encode(createUserRequest.getPassword()), false);
        createUser(createUserRequest, customer);
    }

    public void createAdmin(CreateUserRequest createUserRequest) {
        User admin = new User(createUserRequest.getUsername(), passwordEncoder.encode(createUserRequest.getPassword()), true);
        createUser(createUserRequest, admin);
    }

    private void createUser(CreateUserRequest createUserRequest, User customer) {
        customer.setAddress(createUserRequest.getAddress());
        customer.setName(createUserRequest.getName());
        userRepository.save(customer);
    }

    public void updateCustomer(String username, UpdateUserRequest updateCustomerRequest) {
        User existingCustomer = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if(updateCustomerRequest.getPassword() != null) {
            existingCustomer.setPassword(passwordEncoder.encode(updateCustomerRequest.getPassword()));
        }
        if(updateCustomerRequest.getName() != null) {
            existingCustomer.setName(updateCustomerRequest.getName());
        }
        if(updateCustomerRequest.getAddress() != null) {
            existingCustomer.setAddress(updateCustomerRequest.getAddress());
        }
        userRepository.save(existingCustomer);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
