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
@Table(name = "deliveries")
public class Delivery extends BaseEntity {

    @Builder
    public Delivery(Long id, LocalDate deliveryStart, LocalDate deliveryEnd, Double capacity, Person employee, Person person, Route route, List<DeliveryVehicle> deliveryVehicles) {
        super(id);
        this.deliveryStart = deliveryStart;
        this.deliveryEnd = deliveryEnd;
        this.capacity = capacity;
        this.employee = employee;
        this.person = person;
        this.route = route;
        this.deliveryVehicles = deliveryVehicles;
    }

    @Column(name = "delivery_start_date")
    private LocalDate deliveryStart;

    @Column(name = "delivery_end_date")
    private LocalDate deliveryEnd;

    @Column(name = "capacity")
    private Double capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Person employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
    private List<DeliveryVehicle> deliveryVehicles = new ArrayList<>();
}
