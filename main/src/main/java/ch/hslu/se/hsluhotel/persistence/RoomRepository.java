package ch.hslu.se.hsluhotel.persistence;

import ch.hslu.se.hsluhotel.persistence.model.Room;
import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import java.util.Map;


public interface RoomRepository extends JpaRepository<Room,Long> {

//    @Query("select r from Room r  where not exists (" +
//            "select res from Reservation res where res.room = r and " +
//            "res.checkinDate < ?2 and res.checkoutDate > ?1)")
//    List<Room> findAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);


    @Query("SELECT r from Room r WHERE r.id NOT IN (SELECT re.room.id FROM Reservation re WHERE (:checkoutDate >= re.checkinDate AND :checkinDate <= re.checkoutDate))")
    List<Room> findAvailableRooms(@Param("checkinDate") LocalDate checkinDate, @Param("checkoutDate") LocalDate checkoutDate);



    List<Room> findByRoomType(RoomType roomType);


}


