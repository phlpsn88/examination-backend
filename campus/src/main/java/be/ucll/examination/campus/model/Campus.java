package be.ucll.examination.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name="campus")
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "parking_spots")
    private Integer parkingSpots;

    protected Campus() {}

    public Campus(String name, String address, Integer parkingSpots) {
        this.name = name;
        this.address = address;
        this.parkingSpots = parkingSpots;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getParkingSpots() {
        return parkingSpots;
    }

    public void updateNameAddressAndParkingSpots(String name, String address, Integer parkingSpots) {
        this.name = name;
        this.address = address;
        this.parkingSpots = parkingSpots;
    }
}
