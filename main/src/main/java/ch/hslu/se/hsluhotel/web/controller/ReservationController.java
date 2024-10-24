package ch.hslu.se.hsluhotel.web.controller;

import ch.hslu.se.hsluhotel.persistence.model.Customer;
import ch.hslu.se.hsluhotel.persistence.model.Reservation;
import ch.hslu.se.hsluhotel.service.ReservationService;
import ch.hslu.se.hsluhotel.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for handling reservation-related operations.
 * This class provides endpoints for querying available rooms and creating reservations.
 */

@RestController
@RequestMapping("/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    // Gets the count of available rooms by type for the specified date range.
    @GetMapping("/available-rooms")
    public ResponseEntity<List<Map<String, Long>>> getAvailableRooms(
            @RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
            @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date) {
        Map<String, Long> availableRooms = reservationService.getAvailableRoomsCountByType(start_date, end_date);
        return new ResponseEntity<>(List.of(availableRooms), HttpStatus.OK);
    }

    // Creates a new reservation with customer details.
    @PostMapping("/new")
    public ResponseEntity<?> createReservationWithCustomer(@RequestBody Map<String, Object> payload) {
        String roomType = ((String) payload.get("roomType")).toUpperCase();
        LocalDate startDate = LocalDate.parse((String) payload.get("start_date"));
        LocalDate endDate = LocalDate.parse((String) payload.get("end_date"));
        String name = (String) payload.get("name");
        String email = (String) payload.get("email");

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Room Type: " + roomType);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        Reservation createdReservation = reservationService.createReservation(roomType, startDate, endDate, name, email);

        return new ResponseEntity<>(Map.of("reservationId", createdReservation.getId()), HttpStatus.CREATED);
    }

    // To add booking(reservation) to DB
//    @PostMapping
//    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
//        Map<String, String> errors = validateReservation(reservation);
//
//        if (!errors.isEmpty()) {
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//
//        System.out.println("Creating reservation: " + reservation);
//        Reservation createdReservation = reservationService.createReservation(reservation);
//        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
//    }

//    @PostMapping("/customer")
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//        Customer createdCustomer = reservationService.createCustomer(customer);
//        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//    }



//    private Map<String, String> validateReservation(Reservation reservation) {
//        Map<String, String> errors = new HashMap<>();
//
//        if (reservation.getCheckinDate() == null) {
//            errors.put("checkinDate", "Check-in date is required");
//        }
//        if (reservation.getCheckoutDate() == null) {
//            errors.put("checkoutDate", "Check-out date is required");
//        }
//        if (reservation.getCustomer() == null) {
//            errors.put("customer", "Customer is required");
//        }
//        if (reservation.getRoom() == null) {
//            errors.put("room", "Room is required");
//        }
//
//        return errors;
//    }
}
