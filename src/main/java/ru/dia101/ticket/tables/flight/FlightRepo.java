package ru.dia101.ticket.tables.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findAllByUserId(Long userId);
    List<Flight> findAllByAirlineId(Long airlineId);
    List<Flight> findAllByDepartureCityId(Long departureCityId);
    List<Flight> findAllByArrivalCityId(Long arrivalCityId);
    List<Flight> findAllByPlaneId(Long planeId);
    @Query("" +
            "SELECT f, u, a, de, ar, p " +
            "FROM Flight f " +
            "JOIN User a on f.userId = a.id " +
            "JOIN Airline u on f.airlineId = u.id " +
            "JOIN City de on f.departureCityId = de.id " +
            "JOIN City ar on f.arrivalCityId = ar.id " +
            "JOIN Plane p on f.planeId = p.id " +
            "WHERE f.flightNumber = :flightNumber"
    )
    List<Object[]> findByFlightNumberWithJoin(@Param("flightNumber") String flightNumber);
    @Query("" +
            "SELECT f, u, a, de, ar, p " +
            "FROM Flight f " +
            "JOIN User a on f.userId = a.id " +
            "JOIN Airline u on f.airlineId = u.id " +
            "JOIN City de on f.departureCityId = de.id " +
            "JOIN City ar on f.arrivalCityId = ar.id " +
            "JOIN Plane p on f.planeId = p.id " +
            "WHERE f.id = :id"
    )
    List<Object[]> findByIdWithJoin(@Param("id") Long id);

}
