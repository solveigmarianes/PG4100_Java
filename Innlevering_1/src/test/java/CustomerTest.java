import org.junit.*;

import static junit.framework.TestCase.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class CustomerTest {
    private Customer customer;
    private Thread ivar;
    private RentalAgency agency;
    private Car car;

    @Before
    public void setUp() {
        agency = new RentalAgency();
        car = new Car(1);
        agency.addCarToAgency(car);
        customer = new Customer("Ivar", agency);
        ivar = new Thread(customer);
    }

    @Test
    public void testRun() {
        ivar.start();
        assertEquals(ivar.getState(), Thread.State.RUNNABLE);
        assertTrue(ivar.isAlive());
    }

    @Test
    public void testGetRandomWaitTime() {
        int time = customer.getRandomWaitTime();
        assertTrue(time > 1000 && time < 10000);
    }

    @Test
    public void testGetRandomDriveTime() {
        int time = customer.getRandomDriveTime();
        assertTrue(time > 1000 && time < 3000);
    }
}
