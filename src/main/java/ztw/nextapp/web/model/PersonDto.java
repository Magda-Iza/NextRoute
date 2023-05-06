package ztw.nextapp.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String telephone;
    private String driverLicense;
}
