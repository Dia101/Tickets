package ru.dia101.ticket.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<User>>> findAll(HttpServletRequest request){
        return controllerFunctions.responseWithStatus(request, userService::findAll);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<User>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, userService::findById, request);
    }

    @GetMapping(params = "email")
    public ResponseEntity<ResponseWithStatus<User>> findByEmail(@RequestParam String email, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(email, userService::findByEmail, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody User user, HttpServletRequest request){
        return controllerFunctions.statusCode(user, userService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(HttpServletRequest request){
        return controllerFunctions.statusCode(userService::deleteById, request);
    }

}
