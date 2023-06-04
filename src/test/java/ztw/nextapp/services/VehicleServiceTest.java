package ztw.nextapp.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ztw.nextapp.exceptions.NotEnoughVehiclesException;
import ztw.nextapp.repositories.VehicleRepository;
import ztw.nextapp.web.mapper.VehicleMapper;
import ztw.nextapp.web.model.VehicleDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    public void setUp() {
        vehicles.clear();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
    }

    @Test
    public void findVehiclesForRouteOneVehicleSameLoadTest() {
        // given
        Double load = 100.0;

        // then
        try {
            List<VehicleDto> expectedVehicles = vehicleService.findVehiclesForRouteHelper(load, vehicles);
            assertEquals(1, expectedVehicles.size());
            assertEquals(1, expectedVehicles.get(0).getId());
        } catch (NotEnoughVehiclesException e) {
            fail();
        }
    }

    @Test
    public void findVehiclesForRouteOneVehicleSmallerLoadTest() {
        // given
        Double load = 50.0;

        // then
        try {
            List<VehicleDto> expectedVehicles = vehicleService.findVehiclesForRouteHelper(load, vehicles);
            assertEquals(1, expectedVehicles.size());
            assertEquals(1, expectedVehicles.get(0).getId());
        } catch (NotEnoughVehiclesException e) {
            fail();
        }
    }

    @Test
    public void findVehiclesForRouteOneVehicleBiggerLoadTest() {
        // given
        Double load = 150.0;

        // then
        try {
            List<VehicleDto> expectedVehicles = vehicleService.findVehiclesForRouteHelper(load, vehicles);
            assertEquals(1, expectedVehicles.size());
            assertEquals(2, expectedVehicles.get(0).getId());
        } catch (NotEnoughVehiclesException e) {
            fail();
        }
    }

    @Test
    public void findVehiclesForRouteOneVehicleMiddleListTest() {
        // given
        Double load = 250.0;

        // then
        try {
            List<VehicleDto> expectedVehicles = vehicleService.findVehiclesForRouteHelper(load, vehicles);
            assertEquals(1, expectedVehicles.size());
            assertEquals(3, expectedVehicles.get(0).getId());
        } catch (NotEnoughVehiclesException e) {
            fail();
        }
    }

    @Test
    public void findVehiclesForRouteManyVehiclesTest() {
        // given
        Double load = 950.0;

        // then
        try {
            List<VehicleDto> expectedVehicles = vehicleService.findVehiclesForRouteHelper(load, vehicles);
            assertEquals(4, expectedVehicles.size());
            assert(expectedVehicles.contains(vehicle1));
            assert(expectedVehicles.contains(vehicle2));
            assert(expectedVehicles.contains(vehicle3));
            assert(expectedVehicles.contains(vehicle4));
        } catch (NotEnoughVehiclesException e) {
            fail();
        }
    }

    @Test
    public void findVehiclesForRouteTooBigLoadTest() {
        // given
        Double load = 1500.0;

        // then
        assertThrows(NotEnoughVehiclesException.class, () -> vehicleService.findVehiclesForRouteHelper(load, vehicles));
    }
}
