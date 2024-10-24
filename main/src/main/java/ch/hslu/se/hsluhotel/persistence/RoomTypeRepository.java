package ch.hslu.se.hsluhotel.persistence;

import ch.hslu.se.hsluhotel.persistence.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findByType(String type);



}
