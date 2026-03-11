package be.ucll.examination.campus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // JsonIgnore voorkomt dat je een oneindige lus gaat krijgen
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "floor")
    private Integer floor;

    protected Local() {}

    public Local(String name, String type, Integer capacity, Integer floor) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.floor = floor;
    }

    public Campus getCampus() {
        return campus;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
