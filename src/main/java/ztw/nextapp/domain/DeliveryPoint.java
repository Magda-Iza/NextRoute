package ztw.nextapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "delivery_points")
public class DeliveryPoint extends BaseEntity {

    @Builder
    public DeliveryPoint(Long id, String name, String latitude, String longitude) {
        super(id);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliveryPoint")
    private Set<RoutePoint> routePoints = new HashSet<>();
}
