package ch.hslu.se.hsluhotel.service;

import ch.hslu.se.hsluhotel.persistence.CustomerRepository;
import ch.hslu.se.hsluhotel.persistence.ReservationRepository;
import ch.hslu.se.hsluhotel.persistence.RoomRepository;
import ch.hslu.se.hsluhotel.persistence.RoomTypeRepository;
import ch.hslu.se.hsluhotel.persistence.model.Customer;
import ch.hslu.se.hsluhotel.persistence.model.Reservation;
import ch.hslu.se.hsluhotel.persistence.model.Room;
import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service implementation for handling reservations.
 * This class provides methods for creating reservations and querying available rooms.
 */

@Service
public class ReservationServiceImpl implements ReservationService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomTypeRepository roomTypeRepository;

    // Autowired constructor for dependency injection of the repositories.
    @Autowired
    public ReservationServiceImpl(RoomRepository roomRepository, ReservationRepository reservationRepository, CustomerRepository customerRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    // Gets the count of available rooms grouped by room type for the specified date range.
    @Override
    public Map<String, Long> getAvailableRoomsCountByType(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = roomRepository.findAvailableRooms(checkInDate, checkOutDate);

        return availableRooms.stream()
                .collect(Collectors.groupingBy(room -> room.getRoomType().getType(), Collectors.counting()));
    }

    // Creates a new reservation for a specified room type and date range, and associates it with a customer.
    @Override
    public Reservation createReservation(String roomType, LocalDate startDate, LocalDate endDate, String name, String email) {
        try {
            // Convert roomType string to RoomType enum
            RoomType type = roomTypeRepository.findByType(roomType);

            // Create and save the customer
            Customer customer = new Customer(name, email);
            customer = customerRepository.save(customer);

            // Find the room by type
            List<Room> availableRooms = roomRepository.findAvailableRooms(startDate, endDate)
                    .stream()
                    .filter(room -> room.getRoomType().getType().equals(roomType))
                    .collect(Collectors.toList());
            if (availableRooms.isEmpty()) {
                throw new IllegalArgumentException("No available room of type: " + roomType);
            }
            Room room = availableRooms.get(0);

            // Create and save the reservation
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer);
            reservation.setRoom(room);
            reservation.setCheckinDate(startDate);
            reservation.setCheckoutDate(endDate);

            return reservationRepository.save(reservation);
        } catch (IllegalArgumentException e) {
            // Handle invalid room type
            throw new IllegalArgumentException("Invalid room type: " + roomType, e);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            throw new RuntimeException("Failed to create reservation with customer", e);
        }
    }



//    @Override
//    public Reservation createReservation(Reservation reservation) {
//        // Load the customer and room from the database
//        Customer customer = customerRepository.findById(reservation.getCustomer().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));
//        Room room = roomRepository.findById(reservation.getRoom().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));
//
//        // Set the loaded customer and room to the reservation
//        reservation.setCustomer(customer);
//        reservation.setRoom(room);
//
//        System.out.println("Saving reservation: " + reservation);
//        return reservationRepository.save(reservation);
//    }

//    @Override
//    public Customer createCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }


}
