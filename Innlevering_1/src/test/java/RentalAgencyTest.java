import org.junit.*;

import java.util.*;

import static junit.framework.TestCase.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class RentalAgencyTest {
    private RentalAgency agency;
    private String name = "Ivar";

    @Before
    public void setUp() {
        agency = new RentalAgency();
        for (int i = 0; i < 5; i++) {
            agency.addCarToAgency(new Car(i));
        }
    }

    @Test
    public void testRentCar() {
        Car car = agency.rentCar(name);
        assertTrue("Car is rented out", !car.isAvailable());
        assertTrue("Car is no longer in queue", !agency.getCarsList().contains(car));

        for (int i = 0; i < 4; i++) {
            agency.rentCar(name);
        }
        assertTrue("Queue is empty when all cars are rented out", agency.getRentalCars().isEmpty());
    }

    @Test
    public void testReturnCar() {
        Car car = agency.rentCar(name);
        agency.returnCar(car);
        assertTrue("Car is available after return", car.isAvailable());
        assertTrue("Car is back in queue", agency.getCarsList().contains(car));
    }

    /*
    *   Tester ikke getRentalCars (getter), addCarToAgency (brukes i setUp)
    *   og getColor (veldig simpel kosmetisk metode)
    */

    @Test
    public void testGetCarsList() {
        List<Car> cars = agency.getCarsList();
        assertEquals("List contains 5 cars", cars.size(), 5);
        for (int i = 0; i < 5; i++) {
            assertEquals("Cars are added in correct order", agency.getRentalCars().poll(), cars.get(i));
        }

    }
}
