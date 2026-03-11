package be.ucll.examination.campus.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    // CascadeType.ALL -> auto save locals in local tabel
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL)
    private List<Local> locals = new ArrayList<>();

    protected Campus() {}

    public Campus(String name, String address, Integer parkingSpots) {
        this.name = name;
        this.address = address;
        this.parkingSpots = parkingSpots;
    }

    public void addLocal(Local local) {
        this.locals.add(local);
        local.setCampus(this);
    }

    public List<Local> getLocals() {
        return locals;
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
