package ru.dia101.ticket.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.authentication.routes.components.AuthService;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final ServiceFunctions serviceFunctions;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;


    public ResponseWithStatus<List<User>> findAll(HttpServletRequest request){
        return serviceFunctions.findAllWithAuth(userRepo::findAll, request);
    }
    public ResponseWithStatus<User> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, userRepo::findById, request);
    }
    public ResponseWithStatus<User> findByEmail(String email, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(email, userRepo::findByEmail, request);
    }
    public StatusCode save(User user, HttpServletRequest request){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        return serviceFunctions.saveWithAuth(user, user.getEmail(), userRepo::findByEmail, userRepo::save, request);
    }

    public StatusCode deleteById(HttpServletRequest request){
        User dbUser = authService.getUserByHttpRequest(request);

        if (dbUser == null) {
            return StatusCode.create(403);
        }

        userRepo.deleteById(dbUser.getId());
        return StatusCode.create(200);
    }


}
