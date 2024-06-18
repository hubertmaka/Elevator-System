package pl.hubertmaka.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

class Elevator implements {
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private int elevatorID;
    private int currentFloor;
    private int targetFloor;
    private LinkedList<Integer> requests;


    Elevator(int elevatorID) {
        checkElevatorIdCorrectness(elevatorID);
        logger.info("ELEVATOR WITH ID " + elevatorID + " INITIALIZED");
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
            logger.info("ADDING FLOOR THAT ACTUALLY IS IN REQUESTS. SKIPPING...");
        } else {
            logger.info("ADDING NEW FLOOR TO REQUESTS");
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
        logger.info("UPDATING FLOORS IN ELEVATOR "+ this.elevatorID);
        this.addRequest(targetFloor);
    }

     void step() {
        this.changeFloor();
        this.pollNewRequest();
    }

    private void changeFloor() {
        if (this.currentFloor < this.targetFloor) {
            logger.info("ELEVATOR " + this.elevatorID + " MOVING UP");
            this.currentFloor++;
        } else if (this.currentFloor > this.targetFloor) {
            logger.info("ELEVATOR " + this.elevatorID + " MOVING DOWN");
            this.currentFloor--;
        }
    }

    private void pollNewRequest(){
        if (this.currentFloor == this.targetFloor && !this.requests.isEmpty()) {
            logger.info("ELEVATOR " + this.elevatorID + " POLLING NEW REQUEST");
            this.targetFloor = requests.poll();
            }

    }
    
}
