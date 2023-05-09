package ru.dia101.ticket.tables.plane;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.ticket.files.ResponseWithStatus;
import ru.dia101.ticket.files.ServiceFunctions;
import ru.dia101.ticket.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneService {
    private final PlaneRepo planeRepo;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Plane>> findAll(){
        return ResponseWithStatus.create(200, planeRepo.findAll());
    }
    public ResponseWithStatus<Plane> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, planeRepo::findById, request);
    }
    public ResponseWithStatus<Plane> findBySerialNumber(String serialNumber, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(serialNumber, planeRepo::findBySerialNumber, request);
    }
    public StatusCode save(Plane plane, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                plane,
                plane.getSerialNumber(),
                planeRepo::findBySerialNumber,
                planeRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, planeRepo::findById, planeRepo::deleteById, request);
    }


}