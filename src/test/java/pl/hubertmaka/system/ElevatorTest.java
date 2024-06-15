package pl.hubertmaka.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    private Elevator elevator;

    @BeforeEach
    void setUp() {
        elevator = new Elevator(1);
    }

    @Test
    void testGivenElevatorInstanceWhenGettingIdThenReturnCurrentIdEqualGiven() {
        assertEquals(1, elevator.getElevatorID());
    }

    @Test
    void testGivenElevatorInstanceWhenGettingInitialCurrentFloorThenReturnZero() {
        assertEquals(0, elevator.getCurrentFloor());
    }

    @Test
    void testGivenElevatorInstanceWhenGettingInitialTargetFloorThenReturnZero() {
        assertEquals(0, elevator.getTargetFloor());
    }

    @Test
    void testGivenElevatorInstanceWhenGettingInitialRequestsThenReturnEmptyListOfRequests() {
        assertTrue(elevator.getRequests().isEmpty());
    }

    @Test
    void testGivenInitialElevatorInstanceWhenCheckingIfIdleThenReturnTrue() {
        assertTrue(elevator.isIdle());
    }

    @Test
    void testGivenFloorToEmptyRequestsWhenAddingNewRequestThenUpdateRequestsListToThisFloor() {
        elevator.addRequest(5);
        assertEquals(5, elevator.getRequests().getFirst());
    }

    @Test
    void testGivenFloorToNotEmptyRequestsWhenAddingNewRequestFloorThenUpdateRequestsAndReturnFloorList() {
        elevator.addRequest(5);
        elevator.addRequest(10);
        List<Integer> floorList = new ArrayList<>();
        floorList.add(5);
        floorList.add(10);
        assertEquals(floorList, elevator.getRequests());
    }

    @Test
    void testGivenFloorWhichIsCurrentlyInRequestsWhenAddingNewRequestThenDoNotUpdateRequestsList() {
        elevator.addRequest(5);
        elevator.addRequest(5);
        List<Integer> floorList = new ArrayList<>();
        floorList.add(5);
        assertEquals(floorList, elevator.getRequests());
    }

    @Test
    void testGivenCurrentAndTargetFloorWhenUpdateThenChangeElevatorsCurrentAndTargetFloorsStateToGiven() {
    elevator.update(5, 10);
    assertEquals(5, elevator.getCurrentFloor());
    assertEquals(10, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorLowerThanTargetWhenDoingStepThenIncreaseCurrentFloorByOne() {
        elevator.update(5, 10);
        elevator.step();
        assertEquals(6, elevator.getCurrentFloor());
        assertEquals(10, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateFloorHigherThanTargetWhenDoingStepThenDecreaseCurrentFloorByOne() {
        elevator.update(10, 5);
        elevator.step();
        assertEquals(9, elevator.getCurrentFloor());
        assertEquals(5, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorLowerThanTargetWhenDoingThreeStepThenIncreaseCurrentFloorByThree() {
        elevator.update(5, 10);
        elevator.step();
        elevator.step();
        elevator.step();
        assertEquals(8, elevator.getCurrentFloor());
        assertEquals(10, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateFloorHigherThanTargetWhenDoingThreeStepThenDecreaseCurrentFloorByThree() {
        elevator.update(10, 5);
        elevator.step();
        elevator.step();
        elevator.step();
        assertEquals(7, elevator.getCurrentFloor());
        assertEquals(5, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorEqualsTargetWhenDoingStepThenPollNewRequestAndUpdateTargetFloor() {
        elevator.update(10, 10);
        elevator.addRequest(15);
        elevator.step();
        assertEquals(10, elevator.getCurrentFloor());
        assertEquals(15, elevator.getTargetFloor());
        assertTrue(elevator.getRequests().isEmpty());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorEqualsTargetWhenDoingStepThenPollNewRequest() {
        elevator.update(10, 10);
        elevator.addRequest(15);
        elevator.step();
        assertEquals(10, elevator.getCurrentFloor());
        assertEquals(15, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentFloorAndTargetFloorNotEqualsAndNoRequestsWhenCheckingIsIdleThenReturnFalse() {
        elevator.update(10, 10);
        elevator.addRequest(15);
        elevator.step();
        assertFalse(elevator.isIdle());
    }

    @Test
    void testGivenCurrentFloorAndTargetFloorEqualsAndNoRequestsWhenCheckingIsIdleThenReturnTrue() {
        elevator.update(10, 10);
        elevator.step();
        assertTrue(elevator.isIdle());
    }

    @Test
    void testGivenFloorLowerThanCurrentAndHigherThanTargetAndMovingUpDirectionWhenCheckIsMovingTowardsThenReturnTrue() {
        elevator.update(7, 10);
        assertTrue(elevator.isMovingTowardsFloor(8, 1));
    }

    @Test
    void testGivenFloorHigherThanCurrentAndLowerThanTargetAndMovingDownDirectionWhenCheckIsMovingTowardsThenReturnTrue() {
        elevator.update(20, 10);
        assertTrue(elevator.isMovingTowardsFloor(12, -1));
    }

    @Test
    void testGivenFloorHigherThanCurrentAndLowerThanTargetAndMovingUpDirectionWhenCheckIsMovingTowardsThenReturnFalse() {
        elevator.update(7, 10);
        assertFalse(elevator.isMovingTowardsFloor(12, 1));
    }

    @Test
    void testGivenFloorLowerThanCurrentAndLowerThanTargetAndMovingDownDirectionWhenCheckIsMovingTowardsThenReturnFalse() {
        elevator.update(20, 10);
        assertFalse(elevator.isMovingTowardsFloor(8, -1));
    }

}