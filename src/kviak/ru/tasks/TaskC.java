package kviak.ru.tasks;

import java.util.Scanner;

public class TaskC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        double[][] beacons = new double[K][4];

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 4; j++) {
                beacons[i][j] = scanner.nextDouble();
            }
        }

        double[] location = trilaterate(beacons);
        System.out.println(location[0] + " " + location[1] + " " + location[2]);
    }

    public static double[] trilaterate(double[][] beacons) {
        double X = 0, Y = 0, Z = 0;
        double W = 0;

        for (double[] beacon : beacons) {
            double Xi = beacon[0];
            double Yi = beacon[1];
            double Zi = beacon[2];
            double Di = beacon[3];

            X += (Xi / Di);
            Y += (Yi / Di);
            Z += (Zi / Di);
            W += (1 / Di);
        }

        X = X / W;
        Y = Y / W;
        Z = Z / W;

        double[] location = {X, Y, Z};
        return location;
    }
}
