import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Car {

    private String ID = UUID.randomUUID().toString();
    private int maxPassengers = 6;
    private int currentPassengers = 0;
    private boolean isAvailable = true;
    private boolean isSharable = false;
    private List<HashMap<String, Integer>> passengerList = new ArrayList<>();

    public Car(int maxPassengers, boolean isSharable, boolean isAvailable) {
        this.maxPassengers = maxPassengers;
        this.isSharable = isSharable;
        this.isAvailable = isAvailable;
    }

    public int getCurrentPassengers() {
        return currentPassengers;
    }

    public void setCurrentPassengers(int currentPassengers) {
        this.currentPassengers = currentPassengers;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isIsSharable() {
        return isSharable;
    }

    public void setIsSharable(boolean isSharable) {
        this.isSharable = isSharable;
    }

    public List<HashMap<String, Integer>> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<HashMap<String, Integer>> passengerList) {
        this.passengerList = passengerList;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getID() {
        return ID;
    }
}
