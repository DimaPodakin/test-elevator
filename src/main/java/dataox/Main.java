package dataox;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Building building = new Building((new Random().nextInt(16) + 5));
        System.out.println("START");
        System.out.println(building);
        building.startCycle();
    }
}
