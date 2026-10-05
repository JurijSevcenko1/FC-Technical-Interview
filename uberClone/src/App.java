import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Uber Clone");

        // Create a list of cars with random variables to simulate real world.
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int maxPassengers = (int)(Math.random() * 4) + 3; // maxPassengers between 3 and 6 (reference:https://www.baeldung.com/java-generating-random-numbers-in-range)
            boolean isSharable = Math.random() < 0.5; // isSharable is either true or false
            boolean isAvailable = true;
            Car car = new Car(maxPassengers, isSharable, isAvailable);
            carList.add(car);
        }

        // Print the list of cars
        for (Car car : carList) {
            System.out.println("Car ID: " + car.getID() + ", Max Passengers: " + car.getMaxPassengers() + ", Is Sharable: " + car.isIsSharable() + ", Is Available: " + car.isIsAvailable());
        }

        // Simulate a Booking request - Attain user input
        bookCar();
    }


    static void bookCar() {
        // Process user input and find a suitable car from the list of cars

            // Check whether input number of passengers is less than or equal to 6 - if not throw error.

            // If sharing is selected, cars that are sharable can be considered

            // Sort the list of available cars based on the number of current passengers in ascending order.

            // Search for most suitable car - assign the car with least number of passengers that fills the user request.

        // If a suitable car is found, update the car's currentPassengers and passengerList accordingly
        // Return the car's ID.
    }
}
