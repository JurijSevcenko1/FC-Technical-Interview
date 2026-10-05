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
    }
}
