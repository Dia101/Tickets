package ru.dia101.ticket.tables.airline;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepo airlineRepo;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Airline>> findAll(){
        return ResponseWithStatus.create(200, airlineRepo.findAll());
    }
    public ResponseWithStatus<Airline> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, airlineRepo::findById, request);
    }
    public ResponseWithStatus<Airline> findByName(String name, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(name, airlineRepo::findByName, request);
    }
    public StatusCode save(Airline airline, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                airline,
                airline.getName(),
                airlineRepo::findByName,
                airlineRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, airlineRepo::findById, airlineRepo::deleteById, request);
    }


}