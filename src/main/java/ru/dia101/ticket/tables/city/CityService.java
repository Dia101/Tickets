package ru.dia101.ticket.tables.city;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepo cityRepo;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<City>> findAll(){
        return ResponseWithStatus.create(200, cityRepo.findAll());
    }
    public ResponseWithStatus<City> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, cityRepo::findById, request);
    }
    public ResponseWithStatus<City> findByAirport(String airport, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(airport, cityRepo::findByAirport, request);
    }
    public StatusCode save(City city, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                city,
                city.getAirport(),
                cityRepo::findByAirport,
                cityRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, cityRepo::findById, cityRepo::deleteById, request);
    }


}