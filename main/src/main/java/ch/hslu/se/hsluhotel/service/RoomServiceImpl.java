package ch.hslu.se.hsluhotel.service;

import ch.hslu.se.hsluhotel.Exceptions.RoomNotFoundExeption;
import ch.hslu.se.hsluhotel.persistence.RoomRepository;
import ch.hslu.se.hsluhotel.persistence.RoomTypeRepository;
import ch.hslu.se.hsluhotel.persistence.model.Room;
import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation for handling room-related operations.
 * This class provides methods for finding rooms by ID, checking for available rooms,
 * and retrieving all room types.
 */

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    // Autowired constructor for dependency injection of the repositories.
    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    // Finds a room by its ID.
    @Override
    public Room findRoom(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundExeption(id));
    }

    // Finds available rooms within a specified date range.
    @Override
    public List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
        return roomRepository.findAvailableRooms(checkInDate, checkOutDate);
    }

    // Retrieves all room types.
    @Override
    public List<RoomType> findAllRoomTypes() {
        return roomTypeRepository.findAll();
    }
}

//    @Override
//    public void delete(Long id) {
//        if (employeeRepository.existsById(id)) {
//            employeeRepository.deleteById(id);
//        } else {
//            throw new EmployeeNotFoundException(id);
//        }
//    }

//    @Override
//    public Employee update(Employee employee) {
//        return employeeRepository.save(employee);
//    }

//    @Override
//    public List<Employee> getEmployees(String region) {
//        return employeeRepository.findByRegion(region);
//    }

