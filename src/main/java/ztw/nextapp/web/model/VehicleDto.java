package ztw.nextapp.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDto {
    private Long id;
    private String vin;
    private String brand;
    private String model;
    private Double capacity;
    private LocalDate registerDate;
}
