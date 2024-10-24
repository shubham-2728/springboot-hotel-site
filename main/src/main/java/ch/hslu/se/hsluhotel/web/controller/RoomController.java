package ch.hslu.se.hsluhotel.web.controller;

import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import ch.hslu.se.hsluhotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling room-related operations.
 * This class provides an endpoint for retrieving all room types.
 */

@RestController
@RequestMapping("/room")
@CrossOrigin


public class RoomController {
    private final RoomService roomService;

    // Autowired constructor for dependency injection of the room service.
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Retrieves all room types.
    @GetMapping("/types")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        List<RoomType> roomTypes = roomService.findAllRoomTypes();
        return new ResponseEntity<>(roomTypes, HttpStatus.OK);
    }



}
