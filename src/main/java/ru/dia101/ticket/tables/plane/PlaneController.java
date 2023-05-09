package ru.dia101.ticket.tables.plane;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;
import ru.dia101.ticket.tables.place.Place;
import ru.dia101.ticket.tables.place.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaceService placeService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Place>>> findAll(){
        return ResponseEntity.ok(placeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Place>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, placeService::findById, request);
    }

    @GetMapping(params = "serialNumber")
    public ResponseEntity<ResponseWithStatus<Place>> findByPlaceNumber(@RequestParam String serialNumber, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(serialNumber, placeService::findByPlaceNumber, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Place place, HttpServletRequest request){
        return controllerFunctions.statusCode(place, placeService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, placeService::deleteById, request);
    }

}
