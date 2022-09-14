package dataox.abstraction;

public interface Elevator extends Movable, Directional, Capacious {
    int getCurrentFloor();

    void addPassenger(int passengerFloor);

    int removePassengers();
}
