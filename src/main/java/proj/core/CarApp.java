package proj.core;

import proj.core.car.*;

public class CarApp {
    public static void main(String[] args) {
        CarRepository<RCar> cr1 = new RCarRepository();
        RCar rCar = new RCar(1234L, "010-1111-1111", 2023, 9, 15, 1);
        cr1.save(rCar);
        RCar findRCar = (RCar) cr1.findById(1234L);

        System.out.println("new car = " + rCar.getCarId());
        System.out.println("find car = " + findRCar.getCarId());
        System.out.println("\n");

        CarRepository<GCar> cr2 = new GCarRepository();
        GCar gCar1 = new GCar(2345L, 2023, 9, 16, 13, 50);
        GCar gCar2 = new GCar(3456L, "010-2222-2222", 2024, 1, 10, 10, 10, 2);
        cr2.save(gCar1);
        cr2.save(gCar2);

        GCar findGCar1 = (GCar) cr2.findById(2345L);
        GCar findGCar2 = (GCar) cr2.findById(3456L);

        cr2.delete(gCar1);


        System.out.println("new car1 = " + gCar1.getCarId());
        System.out.println("find car1 = " + findGCar1.getCarId());
        System.out.println("find car2 = " + findGCar2.getCarId());


    }
}
