package ztw.nextapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @Builder
    public Vehicle(Long id, String vin, String brand, String model, Double capacity, LocalDate registerDate) {
        super(id);
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.registerDate = registerDate;
    }

    @Column(name = "vin")
    private String vin;

    @Column(name = "r_number")
    private String rNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private Double capacity;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<DeliveryVehicle> deliveryVehicles = new ArrayList<>();

}
