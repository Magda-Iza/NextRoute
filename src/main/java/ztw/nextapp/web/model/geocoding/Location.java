package ztw.nextapp.web.model.geocoding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@Setter
@Getter
@NoArgsConstructor
public class Location {
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lng")
    private double lng;
}
