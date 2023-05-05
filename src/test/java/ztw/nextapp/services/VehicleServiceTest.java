package ztw.nextapp.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ztw.nextapp.domain.Vehicle;
import ztw.nextapp.repository.VehicleRepository;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VehicleServiceTest {
    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    VehicleMapper vehicleMapper;
    @InjectMocks
    VehicleServiceImpl vehicleService;
    private static List<VehicleDto> vehicles;
    private static VehicleDto vehicle1;
    private static VehicleDto vehicle2;
    private static VehicleDto vehicle3;
    private static VehicleDto vehicle4;

    @BeforeAll
    public static void generalSetUp() {
        vehicles = new ArrayList<>();

        vehicle1 = new VehicleDto();
        vehicle1.setId(1L);
        vehicle1.setCapacity(100.0);

        vehicle2 = new VehicleDto();
        vehicle2.setId(2L);
        vehicle2.setCapacity(200.0);

        vehicle3 = new VehicleDto();
        vehicle3.setId(3L);
        vehicle3.setCapacity(300.0);

        vehicle4 = new VehicleDto();
        vehicle4.setId(4L);
        vehicle4.setCapacity(400.0);
    }

    @Test
    public void findVehiclesForRouteOneVehicleSameLoadTest() {
        // given
        Double load = 100.0;
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);

        // then
        assertEquals(1, vehicleService.findVehiclesForRoute(load).size());
    }
}
