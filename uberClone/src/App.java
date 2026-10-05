import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Uber Clone");

        // Create a list of cars with random variables to simulate real world.
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int maxPassengers = (int) (Math.random() * 4) + 3; // maxPassengers between 3 and 6
                                                               // (reference:https://www.baeldung.com/java-generating-random-numbers-in-range)
            boolean isSharable = Math.random() < 0.5; // isSharable is either true or false
            boolean isAvailable = true;
            Car car = new Car(maxPassengers, isSharable, isAvailable);
            carList.add(car);
        }

        // Print the list of cars
        for (Car car : carList) {
            System.out.println("Car ID: " + car.getID() + ", Max Passengers: " + car.getMaxPassengers()
                    + ", Is Sharable: " + car.isSharable() + ", Is Available: " + car.isAvailable());
        }

        // Take a Booking request - Attain user input with input validation
        Scanner scanner = new Scanner(System.in);
        int passengers = 0;
        while (passengers == 0) {
            System.out.print("How many people is the booking for? (up to 6) : ");
            int inputPassengers = Integer.parseInt(scanner.nextLine());
            if (inputPassengers > 0 && inputPassengers <= 6) {
                passengers = inputPassengers;
            } else {
                System.out.println("Please provide a number between 1 and 6.");
            }
        }

        System.out.print("Are you happy with sharing the ride? (y/n) : ");
        boolean isSharingSet = false;
        boolean isSharing = false;
        while (!isSharingSet) {
            String sharing = scanner.nextLine();

            if (sharing.toLowerCase().compareTo("y") == 0 || sharing.toLowerCase().compareTo("yes") == 0) {
                isSharing = true;
                isSharingSet = true;
            } else if (sharing.toLowerCase().compareTo("n") == 0 || sharing.toLowerCase().compareTo("no") == 0) {
                isSharing = false;
                isSharingSet = true;
            } else {
                System.out.println("Please provide your answer as a single character 'y' or 'n'.");
            }
        }
        bookCar(passengers, isSharing, carList);
    }

    static void bookCar(int passengers, boolean sharing, List<Car> cars) {
        // Process user input and find a suitable car from the list of cars
        try {
            // Check whether input number of passengers is less than or equal to 6 - if not
            // throw error - added redundancy in plan for API handling later on - user input
            // cannot be trusted.
            if (passengers >= 6 || passengers <= 0) {
                throw new ArithmeticException("Invalid number of passengers provided");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        List<Car> availableCars = new ArrayList<>();
        for (Car c : cars) {
            if ((c.isAvailable() == true) &&
                    ((sharing) || (!sharing && c.isSharable() == false)) &&
                    (c.getMaxPassengers() - c.getCurrentPassengers() >= passengers)) {
                availableCars.add(c);
            }
        }

        System.out.println("Original List");
        for (Car c : availableCars) {
            System.out.println(c.getMaxPassengers() + " " + c.isSharable());
        }
        Collections.sort(availableCars, (c1, c2) -> Integer.compare(c1.getMaxPassengers(), c2.getMaxPassengers()));
        System.out.println("\nSorted List");
        for (Car c : availableCars) {
            System.out.println(c.getMaxPassengers() + " " + c.isSharable());
        }

        // If sharing is selected, cars that are sharable can be considered

        // Sort the list of available cars based on the number of current passengers in
        // ascending order.

        // Search for most suitable car - assign the car with least number of passengers
        // that fills the user request.

        // If a suitable car is found, update the car's currentPassengers and
        // passengerList accordingly
        // Return the car's ID.
    }
}
