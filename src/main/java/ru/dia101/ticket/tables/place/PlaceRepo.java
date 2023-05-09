package ru.dia101.ticket.tables.place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepo extends JpaRepository<Place, Long>{

        Optional<Place> findByPlaceNumber(String placeNumber);

}

