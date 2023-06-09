package ru.dia101.ticket.tables.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long>{

        Optional<City> findByAirport(String cityNumber);

}

