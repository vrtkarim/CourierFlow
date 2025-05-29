package com.vrtkarim.courierflow.services;


import com.vrtkarim.courierflow.dto.JwtAuthenticationResponse;
import com.vrtkarim.courierflow.dto.SignInRequest;
import com.vrtkarim.courierflow.dto.SignUpRequest;
import com.vrtkarim.courierflow.dto.UserAuthenticationResponse;
import com.vrtkarim.courierflow.models.User;
import com.vrtkarim.courierflow.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private  final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;




    
    public JwtAuthenticationResponse signup(SignUpRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setImageData(request.getImage());
        user.setName(request.getName());
        user.setVehicleType(request.getVehiculeType());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userService.save(user);
        String token = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(token).build();
    }

    public UserAuthenticationResponse signin(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("something went wrong"));
        String token = jwtService.generateToken(user);
        System.out.println(user.getEmail());
        return  UserAuthenticationResponse.builder().token(token)
                .vehicule(user.getVehicleType())
                .email(user.getEmail())
                .name(user.getName())
                .image(user.getImageData())
                .build();
    }



}
