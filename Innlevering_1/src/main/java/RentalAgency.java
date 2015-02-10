import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class RentalAgency {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private ArrayBlockingQueue<Car> rentalCars;
    private Lock lock = new ReentrantLock(true);
    private Condition carsAvailable = lock.newCondition();
    private Hashtable<Car, String> db = new Hashtable<>(5);

    public RentalAgency() {
        rentalCars = new ArrayBlockingQueue<>(5);
    }

    public Car rentCar(String name) {
        lock.lock();
        try {
            while (rentalCars.isEmpty()) {
                System.out.println(ANSI_YELLOW + "\t\t\t\tWaiting for cars to be returned" + ANSI_RESET);
                carsAvailable.await();
            }

            Car car = rentalCars.poll();
            car.setAvailable(false);
            db.put(car, name);
            printStatus();
            return car;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void returnCar(Car car) {
        lock.lock();
        try {
            car.setAvailable(true);
            rentalCars.offer(car);
            db.put(car, "Available");
            printStatus();
            carsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public void addCarToAgency(Car car) {
        rentalCars.offer(car);
        db.put(car, "Available");
    }

    public ArrayBlockingQueue<Car> getRentalCars() {
        return rentalCars;
    }

    private String getColor(boolean available) {
        if (available) {
            return ANSI_GREEN;
        } else {
            return ANSI_RED;
        }
    }

    public List<Car> getCarsList() {
        List<Car> cars = new ArrayList<>();
        cars.addAll(Arrays.asList(rentalCars.toArray(new Car[5])));
        return cars;
    }

    private void printStatus() {
        Enumeration<Car> cars = db.keys();
        while (cars.hasMoreElements()) {
            Car nextCar = cars.nextElement();
            System.out.printf(getColor(nextCar.isAvailable()) + "Car %-20s" + ANSI_RESET, nextCar.getRegNr());
        }
        System.out.println();
        Collection<String> names = db.values();
        Iterator<String> nameIt = names.iterator();
        for (int i = 0; i < 5; i++) {
            String name = nameIt.next();
            if (name.equals("Available")) {
                System.out.printf("%-24s", name);
            } else System.out.printf("Rented to: %-13s", name);

        }
        System.out.println("\n");
    }

}
