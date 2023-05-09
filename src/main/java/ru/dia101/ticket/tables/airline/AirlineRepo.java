package ru.dia101.ticket.tables.airline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, Long>{

        Optional<Airline> findByName(String name);

}

