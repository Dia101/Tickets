package ru.dia101.ticket.tables.flight;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "flights")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "airline_id")
    private Long airlineId;
    @Column(name = "departure_city_id")
    private Long departureCityId;
    @Column(name = "arrival_city_id")
    private Long arrivalCityId;
    @Column(name = "plane_id")
    private Long planeId;
    private String departureTime;
    private String arrivalTime;
}
