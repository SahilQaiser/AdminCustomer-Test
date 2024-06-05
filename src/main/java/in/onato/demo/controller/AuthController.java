package in.onato.demo.controller;

import in.onato.demo.dto.GetTokenResponse;
import in.onato.demo.dto.GetTokenRequest;
import in.onato.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/get")
    public ResponseEntity<GetTokenResponse> getToken(@RequestBody GetTokenRequest request) {
        return ResponseEntity.ok(authenticationService.getToken(request));
    }
}
