package ch.hslu.se.hsluhotel.service;

import java.time.LocalDate;
import java.util.List;

import ch.hslu.se.hsluhotel.persistence.model.Room;
import ch.hslu.se.hsluhotel.persistence.model.RoomType;


public interface RoomService {


    Room findRoom(long id);

    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);
    List<RoomType> findAllRoomTypes();
}
