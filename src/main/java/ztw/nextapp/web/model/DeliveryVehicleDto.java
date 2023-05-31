package ztw.nextapp.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryVehicleDto {
    private Long id;
    private String vehicle;
    private String driver;
}
