package fuel;
import java.util.Scanner;
import java.util.Locale;

public class Fuel {
    private String carType;
    private int endKms;
    private int startKms;
    private double liters;
    private double pricePerLiter;

    public Fuel(String carType, int endKms, int startKms, double liters, double pricePerLiter) {
        this.carType = carType;
        this.endKms = endKms;
        this.startKms = startKms;
        this.liters = liters;
        this.pricePerLiter = pricePerLiter;
    }

    public int calcDistance(int startKms, int endKms) {
        return endKms - startKms;
    }

    public double calcKmPL(int dist, double liters) {
        if (liters == 0) return 0.0;
        return dist / liters;
    }

    public double calcLiterP100Km(int dist) {
        if (dist == 0) return 0.0;
        return (this.liters * 100.0) / dist;
    }

    public double totalCost() {
        return this.liters * this.pricePerLiter;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.ENGLISH);

        System.out.print("How many fill-ups do you want to enter? ");
        int n = input.nextInt();
        input.nextLine();

        Fuel[] cars = new Fuel[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nFill-up #" + (i + 1));

            System.out.print("Enter car type: ");
            String carType = input.nextLine();

            System.out.print("Enter start km: ");
            int startKms = input.nextInt();

            System.out.print("Enter end km: ");
            int endKms = input.nextInt();

            System.out.print("Enter liters filled: ");
            double liters = input.nextDouble();

            System.out.print("Enter price per liter: ");
            double pricePerLiter = input.nextDouble();
            input.nextLine();

            cars[i] = new Fuel(carType, endKms, startKms, liters, pricePerLiter);
        }

        // نفس التنسيق الموجود في الصورة
        System.out.println("\nGas Mileage Calculations");
        System.out.println("Type of Car    Start Miles End Miles Distance Gallons Price Cost Miles/Gal Gal/Mile");
        System.out.println("================================================================================");

        for (int i = 0; i < n; i++) {
            Fuel f = cars[i];
            int dist = f.calcDistance(f.startKms, f.endKms);
            double kmPerL = f.calcKmPL(dist, f.liters);
            double cost = f.totalCost();
            double literPer100Km = f.calcLiterP100Km(dist);

            // نفس التنسيق الموجود في الصورة
            System.out.print(String.format(Locale.ENGLISH, "%-15s %-11d %-9d %-8d %-7.2f %-5.2f %-5.2f %-10.2f %.4f%n",
                    f.carType, f.startKms, f.endKms, dist, f.liters, 
                    f.pricePerLiter, cost, kmPerL, literPer100Km/100));
        }

        input.close();
    }
}