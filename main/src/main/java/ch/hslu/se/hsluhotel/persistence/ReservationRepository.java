package ch.hslu.se.hsluhotel.persistence;

import ch.hslu.se.hsluhotel.persistence.model.Customer;
import ch.hslu.se.hsluhotel.persistence.model.Reservation;
import ch.hslu.se.hsluhotel.persistence.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByCustomer(Customer customer);





}
