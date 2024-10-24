package ch.hslu.se.hsluhotel.persistence.model;
import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roomType_id")
    private RoomType roomType;
    private int roomNr;

    public Room() {
    }

    public Room(RoomType roomType, int roomNr) {
        this.roomType = roomType;
        this.roomNr = roomNr;
    }

    public Long getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getRoomNr() {
        return roomNr;
    }
}
