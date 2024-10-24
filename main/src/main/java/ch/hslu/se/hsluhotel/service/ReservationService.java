package ch.hslu.se.hsluhotel.service;


import ch.hslu.se.hsluhotel.persistence.model.Customer;
import ch.hslu.se.hsluhotel.persistence.model.Reservation;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

public interface ReservationService {
    Map<String, Long> getAvailableRoomsCountByType(LocalDate checkInDate, LocalDate checkOutDate);
    //Reservation createReservation(Reservation reservation);
    //Customer createCustomer(Customer customer);
    Reservation createReservation(String roomType, LocalDate startDate, LocalDate endDate, String name, String email);

}

