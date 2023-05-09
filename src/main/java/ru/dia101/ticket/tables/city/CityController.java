package ru.dia101.ticket.tables.city;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<City>>> findAll(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<City>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, cityService::findById, request);
    }

    @GetMapping(params = "airport")
    public ResponseEntity<ResponseWithStatus<City>> findByAirport(@RequestParam String airport, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(airport, cityService::findByAirport, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody City city, HttpServletRequest request){
        return controllerFunctions.statusCode(city, cityService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, cityService::deleteById, request);
    }

}
