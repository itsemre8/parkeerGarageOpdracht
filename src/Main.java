import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Garage> garages = new ArrayList<>();
        ArrayList<License> licenses = new ArrayList<>();

        Car car1 = new Car("11-ABA-11", "BMW", "320i", "Black");
        Car car2 = new Car("11-ABB-11", "Audi", "A4", "Grey");
        Car car3 = new Car("11-ABC-11", "Opel", "Zafira", "Blue");
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        Garage garage1 = new Garage(2);
        Garage garage2 = new Garage(3);
        garages.add(garage1);
        garages.add(garage2);

        licenses.add(new License(car1, garage1.getId()));
        licenses.add(new License(car2, garage1.getId()));
        licenses.add(new License(car3, garage2.getId()));

        Car selectedCar = null;
        Garage selectedGarage = null;

        boolean running = true;
        while (running)
        {
            System.out.println("\n=== ParkeerGarage Nijmegen ===");
            System.out.println("Geselecteerde auto:   " + (selectedCar == null ? "geen" : selectedCar.getLicensePlate()));
            System.out.println("Geselecteerde garage: " + (selectedGarage == null ? "geen" : "Garage " + selectedGarage.getId()));
            System.out.println("-------------------------------------");
            System.out.println("1. Auto selecteren");
            System.out.println("2. Garage selecteren");
            System.out.println("3. Auto parkeren");
            System.out.println("4. Auto uit garage halen");
            System.out.println("5. Vrije ruimte opvragen");
            System.out.println("6. Stoppen");
            System.out.print("Keuze: ");

            String keuze = scanner.nextLine();

            try
            {
                switch (keuze)
                {
                    case "1":
                        System.out.println("\nKies een auto:");
                        for (int i = 0; i < cars.size(); i++)
                        {
                            System.out.println((i + 1) + ". " + cars.get(i).getBrand() + " " + cars.get(i).getModel() + " (" + cars.get(i).getLicensePlate() + ")");
                        }
                        System.out.print("Nummer: ");
                        int autoNummer = Integer.parseInt(scanner.nextLine()) - 1;
                        selectedCar = cars.get(autoNummer);
                        System.out.println("Auto geselecteerd: " + selectedCar.getLicensePlate());
                        break;

                    case "2":
                        System.out.println("\nKies een garage:");
                        for (int i = 0; i < garages.size(); i++)
                        {
                            System.out.println((i + 1) + ". Garage " + garages.get(i).getId() + " (vrije plekken: " + garages.get(i).getFreeSpace() + ")");
                        }
                        System.out.print("Nummer: ");
                        int garageNummer = Integer.parseInt(scanner.nextLine()) - 1;
                        selectedGarage = garages.get(garageNummer);
                        System.out.println("Garage geselecteerd: Garage " + selectedGarage.getId());
                        break;

                    case "3":
                        if (selectedCar == null || selectedGarage == null)
                        {
                            System.out.println("Selecteer eerst een auto EN een garage.");
                            break;
                        }
                        License juisteLicense = null;
                        for (License license : licenses)
                        {
                            if (license.getLicenseHolderPlate().equals(selectedCar.getLicensePlate())
                                    && license.getValidInGarageId() == selectedGarage.getId())
                            {
                                juisteLicense = license;
                            }
                        }
                        if (juisteLicense == null)
                        {
                            System.out.println("Deze auto heeft geen geldige vergunning voor garage" + selectedGarage.getId() + ".");
                            break;
                        }
                        if (selectedGarage.parkCar(selectedCar, juisteLicense))
                        {
                            System.out.println("Auto" + selectedCar.getLicensePlate() + "geparkeerd in garage" + selectedGarage.getId());
                        }
                        else
                        {
                            System.out.println("Parkeren mislukt, garage vol of vergunning ongeldig.");
                        }
                        break;

                    case "4":
                        if (selectedCar == null || selectedGarage == null)
                        {
                            System.out.println("Selecteer eerst een auto EN een garage.");
                            break;
                        }
                        selectedGarage.unparkCar(selectedCar);
                        System.out.println("Auto" + selectedCar.getLicensePlate() + "is uit garage" + selectedGarage.getId() + "gehaald");
                        break;

                    case "5":
                        if (selectedGarage == null)
                        {
                            System.out.println("Selecteer eerst een garage.");
                            break;
                        }
                        System.out.println("Garage" + selectedGarage.getId() + "heeft nog" + selectedGarage.getFreeSpace() + "vrije plekken");
                        break;

                    case "6":
                        running = false;
                        System.out.println("Tot ziens");
                        break;

                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            }
            catch (Exception e)
            {
                System.out.println("Ongeldige invoer, probeer opnieuw.");
            }
        }
        scanner.close();
    }
}