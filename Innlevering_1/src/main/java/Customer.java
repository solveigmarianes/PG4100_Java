/**
 * Created by solveigmarianes on 03.02.15.
 */
public class Customer implements Runnable {
    private String name;
    private RentalAgency agency;

    public Customer(String name, RentalAgency agency) {
        setName(name);
        setAgency(agency);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Wait to rent car (1 - 10 seconds)
                Thread.sleep(getRandomWaitTime());

                Car car = agency.rentCar(getName());
                assert Thread.holdsLock(car);

                // Go for a ride! (1 - 3 seconds)
                Thread.sleep(getRandomDriveTime());

                agency.returnCar(car);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RentalAgency getAgency() {
        return agency;
    }

    public void setAgency(RentalAgency agency) {
        this.agency = agency;
    }

    public int getRandomWaitTime() {
        return ((int) (Math.random() * 9000 + 1000));
    }

    public int getRandomDriveTime() {
        return ((int) (Math.random() * 2000 + 1000));
    }
}
