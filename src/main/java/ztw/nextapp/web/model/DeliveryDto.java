package ztw.nextapp.web.model;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ztw.nextapp.domain.Person;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//public class DeliveryDto {
//        private Long id;
//        private LocalDate deliveryStart;
//        private LocalDate deliveryEnd;
//        private Double capacity;
//        private PersonDto employee;
//        private PersonDto person;
//        private Long routeId;
//}


@Getter
@Setter
@NoArgsConstructor
public class DeliveryDto {
        private Long id;
        private LocalDate deliveryStart;
        private LocalDate deliveryEnd;
        private Double capacity;
        private Person employee;
        private String origin;
        private String destination;
        private List<DeliveryPointDto> points;
}

