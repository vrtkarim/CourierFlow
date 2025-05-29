package com.vrtkarim.courierflow.controllers;

import com.vrtkarim.courierflow.dto.*;
import com.vrtkarim.courierflow.services.AuthenticationService;

import com.vrtkarim.courierflow.services.RoutingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

@RestController
public class Controller {

    private final AuthenticationService authenticationService;
    private final RoutingService routingService;

    public Controller(AuthenticationService authenticationService,  RoutingService routingService) {

        this.authenticationService = authenticationService;
        this.routingService = routingService;

    }

    @PostMapping("/signin")
    public UserAuthenticationResponse signin(@RequestBody SignInRequest signInRequest) {
        return  authenticationService.signin(signInRequest);
    }
    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestParam String name, @RequestParam String password, @RequestParam String email, @RequestParam String vehicule, @RequestParam MultipartFile image ) {
        try {
            return authenticationService.signup(new SignUpRequest(vehicule, image.getBytes(), name, email, password));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("/editprofile")
    public ResponseEntity<String> editprofile(){
        return ResponseEntity.ok("Profile updated");
    }
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return new ResponseEntity<>("hello tous le monde", HttpStatus.OK);
    }
    @GetMapping("/authenticated")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> authenticatedEndpoint() {
        return new ResponseEntity<>("hello authenticated user ", HttpStatus.OK);
    }





    @PostMapping("/route")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<?> route(@RequestBody LocationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String contentType = "image/png";
        String headerValue = "attachment; filename=\"" + "test.gpx" + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(routingService.routing(request));

    }
}
