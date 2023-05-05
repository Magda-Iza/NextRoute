package ztw.nextapp.web.model.geocoding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;

@Setter
@Getter
@NoArgsConstructor
public class Response {
    @JsonProperty("results")
    private Result[] results;
}
