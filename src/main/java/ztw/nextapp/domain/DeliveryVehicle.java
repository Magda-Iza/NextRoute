package ztw.nextapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "delivery_vehicle")
public class DeliveryVehicle extends BaseEntity {

    public DeliveryVehicle(Long id) {
        super(id);
    }

    public DeliveryVehicle(Delivery delivery, Vehicle vehicle) {
        super();
        this.delivery = delivery;
        this.vehicle = vehicle;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Person person;
}
