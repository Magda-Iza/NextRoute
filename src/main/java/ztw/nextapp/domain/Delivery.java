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
    public Delivery(Long id, LocalDate delivery_start, LocalDate delivery_end) {
        super(id);
        this.delivery_start = delivery_start;
        this.delivery_end = delivery_end;
    }

    @Column(name = "delivery_start_date")
    private LocalDate delivery_start;

    @Column(name = "delivery_end_date")
    private LocalDate delivery_end;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shipment_id", referencedColumnName = "id")
    private Shipment shipment;

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
