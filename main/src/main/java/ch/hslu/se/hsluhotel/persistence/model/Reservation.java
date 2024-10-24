package ch.hslu.se.hsluhotel.persistence.model;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate checkinDate;
    @NotNull
    private LocalDate checkoutDate;

    @ManyToOne
    //New code
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @NotNull
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id") //Second paramter = new
    @NotNull
    private Room room;

    public Reservation() {}

    public Reservation(LocalDate checkinDate, LocalDate checkoutDate, Customer customer, Room room) {
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.customer = customer;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    // Existing methods
    public String getBookingId() {
        return String.format("HSLU-%04d", id);
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", customer=" + customer +
                ", room=" + room +
                '}';
    }
}
