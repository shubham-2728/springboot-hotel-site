package ch.hslu.se.hsluhotel.persistence;

import ch.hslu.se.hsluhotel.persistence.model.Customer;
import ch.hslu.se.hsluhotel.persistence.model.Reservation;
import ch.hslu.se.hsluhotel.persistence.model.Room;
import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import jakarta.annotation.PostConstruct;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Component class responsible for seeding the database with initial data.
 * This class initializes and saves default customers, room types, and rooms into their
 * repositories upon application startup.
 */

@Component
public class SeedDB {

    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;


    private final String standardRoomShortDesc = "1 double bed";
    private final String standardRoomLongDesc = "In the beautiful, modern standard room there is enough space for two. Additionally, you will find a desk and a few plants will make you feel close to nature in the middle of the city.It is comfortable and makes every guest feel at home.";

    private final String deluxeRoomShortDesc = "1 large double bed";
    private final String deluxeRoomLongDesc = "The spacious and modern deluxe suite is the perfect choice for a weekend for two with one extra-large bed. A television with the full set of free channels is ready to be used. Additionally, you will find a mini fridge with everything your heart desires. A bottle of Champagne will expect you as soon as you enter the room.The lake right in front of our hotel will make you feel close to nature.";

    private final String suiteRoomShortDesc = "1 large king bed, Lake view & Coding desk";
    private final String suiteRoomLongDesc = "This luxurious suite combines comfort, sophistication, and cutting-edge amenities for an unparalleled stay. Enjoy panoramic lake views from the expansive windows and private balcony. The suite features a state-of-the-art coding desk with multiple monitors, ergonomic seating, high-speed internet, and advanced programming tools. Unwind in the opulent Jacuzzi, perfect for relaxing after student challenges. The plush king-sized bed with premium linens ensures restful sleep, while the elegant design creates a cozy, sophisticated atmosphere.The Toggweiler Suite is more than just a place to stay—it’s a sanctuary for the mind and soul, crafted to meet the highest standards of excellence for our most distinguished professor.";

    //Autowired constructor for dependency injection of the repositories.
    @Autowired
    public SeedDB(CustomerRepository customerRepository, ReservationRepository reservationRepository, RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    /**
     * Initializes the database with default data.
     * This method creates and saves customers, room types, and rooms into their respective repositories.
     */

    @PostConstruct
    public void init() {

        Customer c1 = new Customer("Anna", "anna@hslu.mail.ch");
        Customer c2 = new Customer("Bob", "bob@hslu.mail.ch");
        Customer c3 = new Customer("Joe", "Joe@hslu.mail.ch");

        // Save customers to repository
        customerRepository.save(c1);
        customerRepository.save(c2);

        // Create default room types
        RoomType standardRoomType = new RoomType("STANDARD", standardRoomShortDesc, standardRoomLongDesc, 140, 2);
        RoomType deluxeRoomType = new RoomType("DELUXE", deluxeRoomShortDesc, deluxeRoomLongDesc, 360, 3);
        RoomType suiteRoomType = new RoomType("SUITE", suiteRoomShortDesc, suiteRoomLongDesc, 750, 1);

        // Save room types to repository
        roomTypeRepository.save(standardRoomType);
        roomTypeRepository.save(deluxeRoomType);
        roomTypeRepository.save(suiteRoomType);

        // Create default rooms
        Room r11 = new Room(standardRoomType, 11);
        Room r12 = new Room(standardRoomType, 12);
        Room r13 = new Room(deluxeRoomType, 13);
        Room r14 = new Room(suiteRoomType, 21);

        // Save rooms to repository
        roomRepository.save(r11);
        roomRepository.save(r12);
        roomRepository.save(r13);
        roomRepository.save(r14);

//        Reservation res1 = new Reservation(
//                LocalDate.of(2024, 5, 7),
//                LocalDate.of(2024, 5, 9),
//                c1, r11);
//
//        reservationRepository.save(res1);



        //  ---- Test for demo purposes. Do NOT place your test here in a real app!!! ----

        roomRepository.findAvailableRooms(
                LocalDate.of(2024, 5, 6),
                LocalDate.of(2024, 5, 10)).forEach(System.out::println);


    }
}
