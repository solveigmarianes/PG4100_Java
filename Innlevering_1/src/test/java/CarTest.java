import org.junit.*;

import static junit.framework.TestCase.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class CarTest {
    private Car car;

    @Before
    public void setUp() {
        car = new Car(1);
    }

    @Test
    public void testGenerateRegNr() {
        String regNr = car.getRegNr();

        assertTrue("1st char is a letter", Character.isLetter(regNr.charAt(0)));
        assertTrue("2nd char is a letter", Character.isLetter(regNr.charAt(1)));

        for (int i = 2; i < 7; i++) {
            assertTrue("3rd to 7th chars are digits", Character.isDigit(regNr.charAt(i)));
        }

        assertEquals("String is 7 chars long", regNr.length(), 7);
    }
}