package in.onato.demo.service;

import in.onato.demo.dto.GetTokenResponse;
import in.onato.demo.dto.GetTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public GetTokenResponse getToken(GetTokenRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var user = userService.findByUsername(request.getUsername());
        return new GetTokenResponse(jwtService.generateToken(user));
    }
}