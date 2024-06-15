package pl.hubertmaka.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.hubertmaka.system.interfaces.ElevatorInterface;

import java.util.LinkedList;

class Elevator implements ElevatorInterface {
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private int elevatorID;
    private int currentFloor;
    private int targetFloor;
    private boolean isMoving;
    private LinkedList<Integer> requests;


    public Elevator(int elevatorID) {
        logger.info("ELEVATOR WITH ID " + elevatorID + "INITIALIZED");
        this.elevatorID = elevatorID;
        this.currentFloor = 0;
        this.targetFloor = 0;
        this.isMoving = false;
        this.requests = new LinkedList<>();
    }

    public int getElevatorID() {
        return this.elevatorID;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }

    public void addRequest(int floor) {
        if(!requests.contains(floor)) {
            logger.info("ADD FLOOR THAT ACTUALLY IS IN REQUESTS. SKIPPING...");
        } else {
            logger.info("ADDING NEW FLOOR TO REQUESTS");
            requests.add(floor);
        }
    }

    @Override
    public void update(int currentFloor, int targetFloor) {
        logger.info("UPDATING TARGET FLOOR");
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
    }

    
    @Override
    public void step() {

    }

}
