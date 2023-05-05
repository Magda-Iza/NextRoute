package ztw.nextapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipments")
public class Shipment extends BaseEntity {

    @Builder
    public Shipment(Long id, String type, Double capacity) {
        super(id);
        this.type = type;
        this.capacity = capacity;
    }

    @Column(name = "type")
    private String type;

    @Column(name = "capacity")
    private Double capacity;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shipment")
    private Delivery delivery;
}
