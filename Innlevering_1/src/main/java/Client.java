import java.util.*;
import java.util.concurrent.*;

/**
 * Created by solveigmarianes on 03.02.15.
 */
public class Client {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create 10 customers, require input from user
        List<String> premadeCustomerNames = premadeCustomerNames();
        List<Customer> customers = new ArrayList<>();

        // Choose to add customers from user input or from a premade list
        for (int i = 0; i < 10; i++) {
            //customers.add(new Customer(getCustomerName(), agency));
            customers.add(new Customer(premadeCustomerNames.get(i), agency));
        }
        System.out.println(customers.size() + " customers created");

        // Create 5 cars and add to RentalAgency
        for (int i = 1; i <= 5; i++) {
            agency.addCarToAgency(new Car(i));
        }
        System.out.println(agency.getRentalCars().size() + " cars created");

        // Execute customers (pun not intended)
        for (int i = 0; i < 10; i++) {
            executor.execute(customers.get(i));
        }
        executor.shutdown();
    }

    private static String getCustomerName() {
        Scanner in = new Scanner(System.in);
        System.out.print("Skriv inn kundenavn: ");
        return in.nextLine();
    }

    private static List<String> premadeCustomerNames() {
        return Arrays.asList("Jonas", "Eivind", "Martin", "Solveig", "Rune", "Per", "Joakim", "Pia", "Anders", "Kim");
    }
}
