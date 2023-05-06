package ztw.nextapp.domain;

import lombok.Builder;
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
@Table(name = "routes")
public class Route extends BaseEntity {

    @Builder
    public Route(Long id) {
        super(id);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private List<Delivery> deliveries = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private List<RoutePoint> routePoints = new ArrayList<>();

}
