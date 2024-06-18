package pl.hubertmaka.system;

import java.util.LinkedList;

class Elevator {
    private final int elevatorID;
    private int currentFloor;
    private int targetFloor;
    private LinkedList<Integer> requests;


    Elevator(int elevatorID) {
        checkElevatorIdCorrectness(elevatorID);
        this.elevatorID = elevatorID;
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.requests = new LinkedList<>();
    }

    private void checkElevatorIdCorrectness(int elevatorID) {
        if (elevatorID < 0) {
            throw new IllegalArgumentException("ELEVATOR ID MUS BE GREATER THAN 0 BUT IS: " + elevatorID);
        }
    }

    int getElevatorID() {
        return this.elevatorID;
    }

    int getCurrentFloor() {
        return this.currentFloor;
    }

    int getTargetFloor() {
        return this.targetFloor;
    }

    LinkedList<Integer> getRequests() {
        return this.requests;
    }

    void addRequest(int floor) {
        if(this.requests.contains(floor) || this.targetFloor == floor) {
        } else {
            this.requests.add(floor);
        }
    }

    boolean isIdle() {
        return this.currentFloor == this.targetFloor && this.requests.isEmpty();
    }

    boolean isMovingTowardsFloor(int floor, int direction) {
        return (this.currentFloor < floor && this.targetFloor > floor && direction > 0) ||
                (this.currentFloor > floor && this.targetFloor < floor && direction < 0);
    }

    void update(int targetFloor) {
        this.addRequest(targetFloor);
    }

     void step() {
        this.changeFloor();
        this.pollNewRequest();
    }

    private void changeFloor() {
        if (this.currentFloor < this.targetFloor) {
            this.currentFloor++;
        } else if (this.currentFloor > this.targetFloor) {
            this.currentFloor--;
        }
    }

    private void pollNewRequest(){
        if (this.currentFloor == this.targetFloor && !this.requests.isEmpty()) {
            this.targetFloor = requests.poll();
            }

    }

}
