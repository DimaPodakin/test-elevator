package dataox;

import dataox.abstraction.Elevator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building {
    private static final int STEPS_TO_VIEW = 20;
    private static final String SEPARATOR = System.lineSeparator();
    private int floors;
    private Elevator elevator;
    private List<Integer>[] passengersInFloor;
    private Random random = new Random();

    public Building(int floors) {
        this.floors = floors;
        elevator = new ElevatorImpl(floors);
        passengersInFloor = new List[floors];
        fillRandomPassengers();
    }

    public void startCycle() {
        for (int i = 1; i <= STEPS_TO_VIEW; i++) {
            int removedPassengers = this.removePassengersFromLift();
            if (elevator.isEmpty()) {
                elevator.setDirection(this.getElevatorDirectionByMajorPartOfPeople());
            }
            int addedPassengers = this.addPassengersToElevator();
            if (removedPassengers == 0 && addedPassengers == 0) {
                i--;
            } else {
                createRandomPassengers(removedPassengers);
                this.showInformation(i, removedPassengers, addedPassengers);
            }
            elevator.move();
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = floors - 1; i >= 0; i--) {
            if (elevator.getCurrentFloor() != i + 1) {
                result.append(i + 1).append(" floor: ")
                        .append(passengersInFloor[i].toString()).append(SEPARATOR);
            } else {
                result.append(i + 1).append(" floor: ")
                        .append(passengersInFloor[i].toString())
                        .append(" Lift:{").append(elevator).append("}").append(SEPARATOR);
            }
        }
        return result.toString();
    }

    private int addPassengersToElevator() {
        elevator.correctDirection();
        List<Integer> indexesToDelete = new ArrayList<>();
        for (int i = 0; i < passengersInFloor[elevator.getCurrentFloor() - 1]
                .size() && elevator.isFull(); i++) {
            if (elevator.isDirection()) {
                if (passengersInFloor[elevator.getCurrentFloor() - 1]
                        .get(i) > elevator.getCurrentFloor()) {
                    indexesToDelete.add(i);
                    elevator.addPassenger(
                            passengersInFloor[elevator.getCurrentFloor() - 1].get(i));
                }
            } else {
                if (passengersInFloor[elevator.getCurrentFloor() - 1]
                        .get(i) < elevator.getCurrentFloor()) {
                    indexesToDelete.add(i);
                    elevator.addPassenger(
                            passengersInFloor[elevator.getCurrentFloor() - 1].get(i));
                }
            }
        }
        if (indexesToDelete.size() > 0) {
            passengersInFloor[elevator.getCurrentFloor() - 1]
                    .subList(0, indexesToDelete.size()).clear();
        }
        return indexesToDelete.size();
    }

    private int removePassengersFromLift() {
        return elevator.removePassengers();
    }

    private void fillRandomPassengers() {
        for (int i = 0; i < floors; i++) {
            passengersInFloor[i] = fillFloor(i + 1);
        }
    }

    private List<Integer> fillFloor(int currentFloor) {
        List<Integer> floor = new ArrayList<>();
        int passInTheFloor = random.nextInt(11);
        for (int j = 1; j < passInTheFloor; j++) {
            floor.add(createRandomPassenger(currentFloor));
        }
        return floor;
    }

    private int createRandomPassenger(int currentFloor) {
        int passengerTargetFloor = currentFloor;
        while (passengerTargetFloor == currentFloor) {
            passengerTargetFloor = random.nextInt(floors) + 1;
        }
        return passengerTargetFloor;
    }

    private void createRandomPassengers(int count) {
        for (int j = 0; j < count; j++) {
            this.passengersInFloor[elevator.getCurrentFloor() - 1].add(
                    createRandomPassenger(elevator.getCurrentFloor()));
        }
    }

    private boolean getElevatorDirectionByMajorPartOfPeople() {
        if (elevator.getCurrentFloor() == 1) {
            return true;
        } else if (elevator.getCurrentFloor() == floors) {
            return false;
        } else {
            int peoplesWhowantUp = 0;
            for (int i = 0; i < passengersInFloor[elevator.getCurrentFloor() - 1].size(); i++) {
                if (passengersInFloor[elevator.getCurrentFloor() - 1]
                        .get(i) > elevator.getCurrentFloor()) {
                    peoplesWhowantUp++;
                }
            }
            return passengersInFloor[elevator.getCurrentFloor() - 1]
                    .size() - peoplesWhowantUp < peoplesWhowantUp;
        }
    }

    private void showInformation(int step, int removedPassengers, int addedPassengers) {
        System.out.println("*********** Step " + step + " ***********");
        System.out.print(this);
        System.out.println("Leave: " + removedPassengers + " Entry: "
                + addedPassengers + SEPARATOR);
    }
}
