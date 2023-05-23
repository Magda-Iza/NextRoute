package ztw.nextapp.web.model;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDto {
        private Long id;
        private LocalDate deliveryStart;
        private LocalDate deliveryEnd;
        private Double capacity;
        private PersonDto employee;
        private PersonDto person;
        private Long routeId;
}
