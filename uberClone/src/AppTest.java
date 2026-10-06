import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private List<Car> cars = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car(3, true, true));
        cars.add(new Car(3, true, true));
        cars.add(new Car(3, false, true));
        cars.add(new Car(3, false, false));
    }

    @Test
    public void bookCarTest() {
        Car car = cars.get(0);

        // Split output due to generated UUID of user
        String desiredOutputStart = "\nCar: " + car.getID() +
                "\npassengers: 2/" + car.getMaxPassengers() +
                "\npassenger list: [{";
        String desiredOutputEnd = "=2}]\navailable: " + car.isAvailable() +
                "\nsharable: " + car.isCurrentlySharable();

        String output = App.bookCar(2, true, cars);
        String outputStart = output.substring(0, output.indexOf("{") + 1);
        String outputEnd = output.substring(output.indexOf("="));
        assertEquals(desiredOutputStart, outputStart);
        assertEquals(desiredOutputEnd, outputEnd);
    }

    @Test
    public void bookForTooManyPassengers() {
        // This tests that an exception is thrown if too many passengers are provided
        String booking = App.bookCar(7, false, cars);
        String expectedMessage = "Invalid number of passengers provided";

        assertEquals(expectedMessage, booking);
    }

    @Test
    public void carsUnavailable() {
        // This tests that an exception is thrown if too many passengers are provided
        cars.clear();
        cars.add(new Car(3, false, false));

        String booking = App.bookCar(3, false, cars);
        String expectedMessage = "No cars currently available";

        assertEquals(expectedMessage, booking);
    }

    @Test
    public void bookLargeCarTest() {
        // This tests that the method picks the correct car
        Car car = new Car(6, false, true);
        cars.add(car);

        String booking = App.bookCar(5, false, cars);
        assertEquals(car.getID(), booking.substring(booking.indexOf(":") + 1, booking.indexOf("\npas")).strip());
    }

    @Test
    public void bookCarNonSharingUser() {
        cars.clear();
        Car car = new Car(3, true, true);
        cars.add(car);
        cars.add(new Car(4, true, true));
        cars.add(new Car(5, false, true));
        cars.add(new Car(3, false, true));

        String booking = App.bookCar(1, false, cars);
        assertEquals(car.getID(), booking.substring(booking.indexOf(":") + 1, booking.indexOf("\npas")).strip());
    }

    @Test
    public void bookCarNonSharingUsers() {
        cars.clear();
        Car car1 = new Car(3, true, true);
        cars.add(car1);
        Car car2 = new Car(4, true, true);
        cars.add(car2);
        cars.add(new Car(5, false, true));
        Car car3 = new Car(3, false, true);
        cars.add(car3);

        String booking1 = App.bookCar(1, false, cars);
        assertEquals(car1.getID(), booking1.substring(booking1.indexOf(":") + 1, booking1.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car1)).isCurrentlySharable(), false);
        assertEquals(cars.get(cars.indexOf(car1)).isAvailable(), false);
        String booking2 = App.bookCar(2, false, cars);
        assertEquals(car3.getID(), booking2.substring(booking2.indexOf(":") + 1, booking2.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car3)).isCurrentlySharable(), false);
        assertEquals(cars.get(cars.indexOf(car3)).isAvailable(), false);
        String booking3 = App.bookCar(2, false, cars);
        assertEquals(car2.getID(), booking3.substring(booking3.indexOf(":") + 1, booking3.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car2)).isCurrentlySharable(), false);
        assertEquals(cars.get(cars.indexOf(car2)).isAvailable(), false);
    }

    @Test
    public void bookCarSharingUser() {
        cars.clear();
        Car car = new Car(3, true, true);
        cars.add(car);
        cars.add(new Car(4, true, true));
        cars.add(new Car(5, false, true));
        cars.add(new Car(3, false, true));

        String booking = App.bookCar(1, true, cars);
        assertEquals(car.getID(), booking.substring(booking.indexOf(":") + 1, booking.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car)).isCurrentlySharable(), true);
        assertEquals(cars.get(cars.indexOf(car)).isAvailable(), true);
    }

    @Test
    public void bookCarSharingUsers() {
        // This tests whether sharing users will be correctly placed into sharable cars
        // and what the status of these cars will be
        cars.clear();
        Car car1 = new Car(3, true, true);
        cars.add(car1);
        Car car2 = new Car(4, true, true);
        cars.add(car2);
        cars.add(new Car(5, false, true));
        Car car3 = new Car(3, false, true);
        cars.add(car3);

        String booking1 = App.bookCar(1, true, cars);
        assertEquals(car1.getID(), booking1.substring(booking1.indexOf(":") + 1, booking1.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car1)).isCurrentlySharable(), true);
        assertEquals(cars.get(cars.indexOf(car1)).isAvailable(), true);

        String booking2 = App.bookCar(2, true, cars);
        assertEquals(car1.getID(), booking2.substring(booking2.indexOf(":") + 1, booking2.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car1)).isCurrentlySharable(), false);
        assertEquals(cars.get(cars.indexOf(car1)).isAvailable(), false);

        String booking3 = App.bookCar(4, true, cars);
        assertEquals(car2.getID(), booking3.substring(booking3.indexOf(":") + 1, booking3.indexOf("\npas")).strip());
        assertEquals(cars.get(cars.indexOf(car2)).isCurrentlySharable(), false);
        assertEquals(cars.get(cars.indexOf(car2)).isAvailable(), false);
    }

    @Test
    public void multiBookCar() {
        // Detailed test of shared booking
        Car car = cars.get(0);

        assertEquals(car.getPassengerList(), new ArrayList<>());

        // Split output due to generated UUID of users
        String desiredOutputStart = "\nCar: " + car.getID() +
                "\npassengers: 2/" + car.getMaxPassengers() +
                "\npassenger list: [";
        String desiredOutputEnd = "]\navailable: " + car.isAvailable() +
                "\nsharable: " + car.isCurrentlySharable();

        // Perform booking 1
        String output = App.bookCar(2, true, cars);

        // Check passenger list
        assertEquals(cars.get(0).getPassengerList().size(), 1);
        String output1PassengerList = output.substring(output.indexOf("{"), output.indexOf("}"));
        String[] pL1 = output1PassengerList.split("=");
        assertEquals(pL1[1], "2");

        // Check full booking status
        String output1Start = output.substring(0, output.indexOf("{"));
        String output1End = output.substring(output.indexOf("]"));
        assertEquals(desiredOutputStart, output1Start);
        assertEquals(desiredOutputEnd, output1End);

        // Car status should change:
        String desiredOutput2Start = "\nCar: " + car.getID() +
                "\npassengers: 3/" + car.getMaxPassengers() +
                "\npassenger list: [";
        String desiredOutput2End = "]\navailable: false\nsharable: false";

        // Perform booking 2
        String output2 = App.bookCar(1, true, cars);
        car = cars.get(0);
        assertEquals(car.getCurrentPassengers(), 3);

        // Check passenger list
        assertEquals(cars.get(0).getPassengerList().size(), 2);
        String output2PassengerList = output2.substring(output2.indexOf("{"), output2.indexOf("]"));
        String[] pL2 = output2PassengerList.split("=");
        assertEquals(pL2[1].substring(0, 1), "2");
        assertEquals(pL2[2].substring(0, 1), "1");

        // Check final full booking status
        String output2Start = output2.substring(0, output2.indexOf("{"));
        String output2End = output2.substring(output2.indexOf("]"));
        assertEquals(desiredOutput2Start, output2Start);
        assertEquals(desiredOutput2End, output2End);

        // Car original sharable status should remain true to be able to revert back to
        // it once someone exits the vehicle.
        assertEquals(car.isSharable(), true);
    }
}
