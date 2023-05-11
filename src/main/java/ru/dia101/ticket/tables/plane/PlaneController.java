package ru.dia101.ticket.tables.plane;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;
import ru.dia101.ticket.tables.plane.Plane;
import ru.dia101.ticket.tables.plane.PlaneService;

import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Plane>>> findAll(){
        return ResponseEntity.ok(planeService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Plane>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, planeService::findById, request);
    }

    @GetMapping(params = "serialNumber")
    public ResponseEntity<ResponseWithStatus<Plane>> findBySerialNumber(@RequestParam String serialNumber, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(serialNumber, planeService::findBySerialNumber, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Plane plane, HttpServletRequest request){
        return controllerFunctions.statusCode(plane, planeService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, planeService::deleteById, request);
    }

}
