import java.util.ArrayList;

public class Garage
{

    private static int counter = 1;
    private ArrayList<Car> parkedCars;
    private int id;
    private int maxCapacity;

    public Garage(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
        this.parkedCars = new ArrayList<>();
        this.id = counter;
        counter++;
    }

    public boolean checkIfValid(Car car, License license)
    {
        if (parkedCars.size() >= maxCapacity)
        {
            return false;
        }

        if (!license.getLicenseHolderPlate().equals(car.getLicensePlate()))
        {
            return false;
        }

        if (license.getValidInGarageId() != id)
        {
            return false;
        }
        return true;
    }

    public boolean parkCar(Car car, License license)
    {
        if (checkIfValid(car, license))
        {
            parkedCars.add(Car);
            return true;
        }
        return false;
    }

    public void unparkCar(Car car)
    {
        if (parkedCars.contains(car))
        {
            parkedCars.remove(car);
        }
    }

    public int getId()
    {
        return id;
    }

    public int getCapacity()
    {
        return parkedCars.size();
    }

    public int getFreeSpace()
    {
        return maxCapacity = parkedCars.size();
    }

    public Car getCarByLicense(String license)
    {
        for (Car car : parkedCars)
        {
            if (car.getLicensePlate().equals(license))
            {
                return car;
            }
        }
        return null;
    }

    public ArrayList<Car> getCarsByModel(String model)
    {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : parkedCars)
        {
            if (car.getModel().equals(model))
            {
                result.add(car);
            }
        }
        return result;
    }
}