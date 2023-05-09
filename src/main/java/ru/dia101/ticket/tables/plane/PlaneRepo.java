package ru.dia101.ticket.tables.plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlaneRepo extends JpaRepository<Plane, Long>{

        Optional<Plane> findBySerialNumber(String serialNumber);

}

