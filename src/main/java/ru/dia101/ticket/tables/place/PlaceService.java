package ru.dia101.ticket.tables.place;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepo placeRepo;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Place>> findAll(){
        return ResponseWithStatus.create(200, placeRepo.findAll());
    }
    public ResponseWithStatus<Place> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, placeRepo::findById, request);
    }
    public ResponseWithStatus<Place> findByPlaceNumber(String placeNumber, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(placeNumber, placeRepo::findByPlaceNumber, request);
    }
    public StatusCode save(Place place, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                place,
                place.getPlaceNumber(),
                placeRepo::findByPlaceNumber,
                placeRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, placeRepo::findById, placeRepo::deleteById, request);
    }


}