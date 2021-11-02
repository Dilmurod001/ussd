package uz.dilmurod.appussd.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.dilmurod.appussd.entity.User;
import uz.dilmurod.appussd.payload.ApiResponse;
import uz.dilmurod.appussd.payload.AuthRegisterDTO;
import uz.dilmurod.appussd.payload.LoginDto;
import uz.dilmurod.appussd.repository.RoleRepository;
import uz.dilmurod.appussd.repository.UserRepository;
import uz.dilmurod.appussd.security.JwtProvider;
import uz.dilmurod.appussd.util.Constants;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return byEmail.get();
        }
        throw new UsernameNotFoundException("Bunday email mavjud emas");
    }

    public ApiResponse registerUser(AuthRegisterDTO authRegisterDTO) throws NotFoundException {
        boolean exists = userRepository.existsByEmail(authRegisterDTO.getEmail());
        if (exists) return new ApiResponse("Bu email ro'yxatda mavjud", false);

        if (!authRegisterDTO.getPrePassword().equals(authRegisterDTO.getPassword()))
            return new ApiResponse("PrePassword noto'g'ri", false);

        User user = new User(authRegisterDTO.getFullName(), authRegisterDTO.getEmail(), passwordEncoder.encode( authRegisterDTO.getPassword()), roleRepository.findByName(Constants.USER).orElseThrow(() -> new NotFoundException("Bunday Role mavjud emas")), true);
        userRepository.save(user);
        return new ApiResponse("Ro'yxatdan muvaffaqiyatli o'tdingiz", true);
    }

    public ApiResponse loginUser(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user=(User)authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getEmail(), user.getRole());
        return new ApiResponse("OK",true,token);
    }
}
