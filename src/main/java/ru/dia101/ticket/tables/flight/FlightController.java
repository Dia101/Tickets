package ru.dia101.ticket.tables.flight;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.ticket.files.ControllerFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {
    private final ControllerFunctions controllerFunctions;
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAll(){
        return ResponseEntity.ok(flightService.findAll());
    }

    @GetMapping(params = "userId")
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAllByUserId(@Param("userId") Long userId){
        return ResponseEntity.ok(flightService.findAllByUserId(userId));
    }

    @GetMapping(params = "airlineId")
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAllByAirlineId(@Param("airlineId") Long airlineId){
        return ResponseEntity.ok(flightService.findAllByAirlineId(airlineId));
    }

    @GetMapping(params = "departureCityId")
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAllByDepartureCityId(@Param("departureCityId") Long departureCityId){
        return ResponseEntity.ok(flightService.findAllByDepartureCityId(departureCityId));
    }

    @GetMapping(params = "arrivalCityId")
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAllByArrivalCityId(@Param("arrivalCityId") Long arrivalCityId){
        return ResponseEntity.ok(flightService.findAllByArrivalCityId(arrivalCityId));
    }

    @GetMapping(params = "planeId")
    public ResponseEntity<ResponseWithStatus<List<Flight>>> findAllByPlaneId(@Param("planeId") Long planeId){
        return ResponseEntity.ok(flightService.findAllByPlaneId(planeId));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<FlightIsFull>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, flightService::findById);
    }

    @GetMapping("flightNumber")
    public ResponseEntity<ResponseWithStatus<FlightIsFull>> findByFlightNumber(@Param("flightNumber") String flightNumber){
        return controllerFunctions.responseWithStatus(flightNumber, flightService::findByFlightNumber);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Flight flight, HttpServletRequest request){
        return controllerFunctions.statusCode(flight, flightService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, flightService::deleteById, request);
    }


}
