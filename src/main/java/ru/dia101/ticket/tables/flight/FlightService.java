package ru.dia101.ticket.tables.flight;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.StatusCode;
import ru.dia101.ticket.users.User;
import ru.dia101.ticket.users.UserRepo;
import ru.dia101.ticket.tables.airline.Airline;
import ru.dia101.ticket.tables.airline.AirlineRepo;
import ru.dia101.ticket.tables.city.City;
import ru.dia101.ticket.tables.city.CityRepo;
import ru.dia101.ticket.tables.plane.Plane;
import ru.dia101.ticket.tables.plane.PlaneRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepo flightRepo;
    private final ServiceFunctions serviceFunctions;
    private final UserRepo userRepo;
    private final AirlineRepo airlineRepo;
    private final CityRepo cityRepo;
    private final PlaneRepo planeRepo;

    private FlightIsFull isFull(List<Object[]> entitiesArr) {
        Object[] entities = entitiesArr.get(0);
        Flight flight = (Flight) entities[0];
        User user = (User) entities[1];
        Airline airline = (Airline) entities[2];
        City departureCity = (City) entities[3];
        City arrivalCity = (City) entities[4];
        Plane plane = (Plane) entities[5];

        return FlightIsFull.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .user(user)
                .airline(airline)
                .departureCity(departureCity)
                .arrivalCity(arrivalCity)
                .plane(plane)
                .build();
    }
    public boolean notFull(Flight flight) {
       if(!userRepo.existsById(flight.getUserId())){
           return true;
       }
        if(!airlineRepo.existsById(flight.getAirlineId())){
            return true;
        }
        if(!cityRepo.existsById(flight.getDepartureCityId())){
            return true;
        }
        if(!cityRepo.existsById(flight.getArrivalCityId())){
            return true;
        }
        if(!planeRepo.existsById(flight.getPlaneId())){
            return true;
        }
       return false;
    }

    public ResponseWithStatus<List<Flight>> findAll(){
        return ResponseWithStatus.create(200, flightRepo.findAll());
    }

    public ResponseWithStatus<List<Flight>> findAllByUserId(Long userId){
        return ResponseWithStatus.create(200, flightRepo.findAllByAirlineId(userId));
    }


    public ResponseWithStatus<List<Flight>> findAllByAirlineId(Long airlineId){
        return ResponseWithStatus.create(200, flightRepo.findAllByAirlineId(airlineId));
    }

    public ResponseWithStatus<List<Flight>> findAllByDepartureCityId(Long departureCityId){
        return ResponseWithStatus.create(200, flightRepo.findAllByDepartureCityId(departureCityId));
    }

    public ResponseWithStatus<List<Flight>> findAllByArrivalCityId(Long arrivalCityId){
        return ResponseWithStatus.create(200, flightRepo.findAllByArrivalCityId(arrivalCityId));
    }

    public ResponseWithStatus<List<Flight>> findAllByPlaneId(Long planeId){
        return ResponseWithStatus.create(200, flightRepo.findAllByPlaneId(planeId));
    }


    public ResponseWithStatus<FlightIsFull> findById(Long id){
        return serviceFunctions.findByWithJoin(
                id,
                flightRepo::findByIdWithJoin,
                this::isFull
        );
    }

    public ResponseWithStatus<FlightIsFull> findByFlightNumber(String flightNumber){
        return serviceFunctions.findByWithJoin(
                flightNumber,
                flightRepo::findByFlightNumberWithJoin,
                this::isFull
        );
    }

    public StatusCode save(Flight flight, HttpServletRequest request){
        return serviceFunctions.saveWithCheckFieldsWithAuth(
                flight,
                this::notFull,
                flightRepo::save,
                request
        );
    }

}
