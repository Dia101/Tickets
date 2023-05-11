package ru.dia101.ticket.tables.airline;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;
import ru.dia101.ticket.tables.city.City;

import java.util.List;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Airline>>> findAll(){
        return ResponseEntity.ok(airlineService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Airline>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, airlineService::findById, request);
    }

    @GetMapping(params = "name")
    public ResponseEntity<ResponseWithStatus<Airline>> findByName(@RequestParam String name, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(name, airlineService::findByName, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Airline airline, HttpServletRequest request){
        return controllerFunctions.statusCode(airline, airlineService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, airlineService::deleteById, request);
    }

}
