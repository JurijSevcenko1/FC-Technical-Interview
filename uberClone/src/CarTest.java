import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    public void initialiseCar() {

        Car car = new Car(5, true, true);

        // // Test assignable variables
        assertEquals(car.getMaxPassengers(), 5);
        assertEquals(car.isSharable(), true);
        assertEquals(car.isAvailable(), true);

        // Test automatic variables
        assertInstanceOf(String.class, car.getID());
        assertEquals(car.getCurrentPassengers(), 0);
        System.out.print(car.isCurrentlySharable());
        assertEquals(car.isCurrentlySharable(), true);
        assertEquals(car.getPassengerList(), new ArrayList<>());
    }

    @Test
    public void setCurrentPassengers() {
        Car car = new Car(5, true, true);
        assertEquals(car.getCurrentPassengers(), 0);

        car.setCurrentPassengers(4);
        assertEquals(car.getCurrentPassengers(), 4);
    }

    @Test
    public void setIsAvailable() {
        Car car = new Car(5, true, true);
        assertEquals(car.isAvailable(), true);

        car.setIsAvailable(false);
        assertEquals(car.isAvailable(), false);
    }

    @Test
    public void setIsSharable() {
        Car car = new Car(5, true, true);
        assertEquals(car.isSharable(), true);
        assertEquals(car.isCurrentlySharable(), true);

        car.setIsSharable(false);
        assertEquals(car.isSharable(), false);
        assertEquals(car.isCurrentlySharable(), false);
    }

    @Test
    public void setIsCurrentlySharable() {
        Car car = new Car(5, true, true);
        assertEquals(car.isCurrentlySharable(), true);

        car.setIsCurrentlySharable(false);
        assertEquals(car.isCurrentlySharable(), false);
    }

    @Test
    public void setPassengerList() {
        Car car = new Car(5, true, true);
        assertEquals(car.getPassengerList(), new ArrayList<>());

        List<HashMap<String, Integer>> newPassengerList = new ArrayList<>();
        HashMap<String, Integer> passengerInfo = new HashMap<String, Integer>();
        passengerInfo.put(UUID.randomUUID().toString(), 3);
        newPassengerList.add(passengerInfo);
        car.setPassengerList(newPassengerList);

        assertEquals(car.getPassengerList(), newPassengerList);
    }
}
