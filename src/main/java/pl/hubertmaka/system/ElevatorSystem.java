package pl.hubertmaka.system;

import pl.hubertmaka.system.interfaces.ElevatorSystemInterface;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem  implements ElevatorSystemInterface {

    private final static int MAX_ELEVATORS = 16;
    private final List<Elevator> elevators = new ArrayList<>();

    public ElevatorSystem(int elevatorsNumber) {
        createElevators(elevatorsNumber);
    }

    private boolean checkElevatorsNumberCorrectness(int elevatorsNumber) {
        if (elevatorsNumber <= 0 || elevatorsNumber > MAX_ELEVATORS) {
            throw new IllegalArgumentException(
                    "Elevators number must be greater than 0 and lesser than"
                            + MAX_ELEVATORS + "but is: " + elevatorsNumber
            );
        }
        return true;
    }

    private void createElevators(int elevatorsNumber) {
        if (checkElevatorsNumberCorrectness(elevatorsNumber)) {
            for(int id = 0; id < elevatorsNumber; id++) {
                this.elevators.add(new Elevator(id));
            }
        }
    }

    public List<Integer> getElevators() {
        List<Integer> idList = new ArrayList<>();
        for (Elevator elevator: this.elevators) {
            idList.add(elevator.getElevatorID());
        }
        return idList;
    }

    @Override
    public List<ArrayList<Integer>> status() {
        List<ArrayList<Integer>> elevatorsStatus = new ArrayList<>();
        for (Elevator elevator: this.elevators) {
            ArrayList<Integer> elevatorAttributes = new ArrayList<>();
            elevatorAttributes.add(elevator.getElevatorID());
            elevatorAttributes.add(elevator.getCurrentFloor());
            elevatorAttributes.add(elevator.getTargetFloor());
            elevatorsStatus.add(elevatorAttributes);
        }
        return elevatorsStatus;
    }

    @Override
    public void pickup(int floor, int direction) {
        Elevator choosenElevator = null;
        for (Elevator elevator: this.elevators) {
            if (elevator.isIdle()) {
                choosenElevator = elevator;
                break;
            } else if (elevator.isMovingTowardsFloor(floor, direction)) {
                choosenElevator = elevator;
                break;
            }
        }
        if (choosenElevator == null) {
            choosenElevator = this.findClosestElevator(floor);
        }

        if (choosenElevator != null) {
            choosenElevator.update(floor);
        }
    }

    private Elevator findClosestElevator(int floor) {
        Elevator closestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator elevator: this.elevators) {
            int distanceToFloor = Math.abs(elevator.getCurrentFloor() - floor);
            if (distanceToFloor < minDistance) {
                closestElevator = elevator;
                minDistance = distanceToFloor;
            }
        }
        return closestElevator;
    }

    @Override
    public void update(int elevatorId, int targetFloor) {
        if (elevatorId < 0 || elevatorId >= MAX_ELEVATORS) {
            throw new IllegalArgumentException(
                    "Elevators ID must cannot be lower than 0 and greater than "
                            + MAX_ELEVATORS + " but is: " + elevatorId);
        }
        elevators.get(elevatorId).update(targetFloor);
    }

    @Override
    public void step() {
        for (Elevator elevator: this.elevators) {
            elevator.step();
        }
    }
}
