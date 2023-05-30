package ztw.nextapp.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryPointDto {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
}
