import java.util.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class Car {
    private boolean available;
    private String regNr;
    private int carNumber;

    public Car(int carNumber) {
        regNr = generateRegNr();
        setAvailable(true);
        setCarNumber(carNumber);
    }

    private String generateRegNr() {
        Random r = new Random();
        char a = (char) (r.nextInt(26) + 'A');
        char b = (char) (r.nextInt(26) + 'A');
        String regNr = String.valueOf(a) + String.valueOf(b);
        for (int i = 0; i < 5; i++) {
            regNr += String.valueOf(r.nextInt(10));
        }
        return regNr;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }


    public String getRegNr() {
        return regNr;
    }
}
