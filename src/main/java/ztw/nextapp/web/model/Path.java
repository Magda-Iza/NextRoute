package ztw.nextapp.web.model;

import com.google.maps.model.LatLng;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Path {
    private List<LatLng> encoded;
}
