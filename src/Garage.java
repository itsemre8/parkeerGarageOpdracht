import java.util.ArrayList;

public class Garage {

    private static int counter = 1;
    private ArrayList<Car> parkedCars;
    private int id;
    private int maxCapacity;

    public Garage(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.parkedCars = new ArrayList<>();
        this.id = counter;
        counter++;
    }

    public boolean checkIfValid(Car car, License license) {
        if (parkedCars.size() >= maxCapacity) {
            return false;
        }

        if (!license.getLicenseHolderPlate().equals(car.getLicensePlate())) {
            return false;
        }

        if (license.getValidInGarageId() != id) {
            return false;
        }
        return true;
    }
}