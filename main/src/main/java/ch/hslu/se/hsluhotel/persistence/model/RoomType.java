package ch.hslu.se.hsluhotel.persistence.model;

import jakarta.persistence.*;

@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String shortDescription;
    @Column(length = 800)
    private String longDescription;
    private double price;
    private int noPers;

    protected RoomType() { }

    public RoomType(String type, String shortDesc, String longDesc, double price, int noPers) {
        this.type = type;
        this.shortDescription = shortDesc;
        this.longDescription = longDesc;
        this.price = price;
        this.noPers = noPers;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public double getPrice() {
        return price;
    }

    public int getNoPers() {
        return noPers;
    }
}

