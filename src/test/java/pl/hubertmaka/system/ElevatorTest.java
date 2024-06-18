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
    void testGivenElevatorIdLowerThanZeroWhenCreatingInstanceThenRaiseIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Elevator(-1),
                "ELEVATOR ID MUS BE GREATER THAN 0 BUT IS: " + -1
        );
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
    void testGivenCurrentAndTargetFloorWhenAddRequestThenChangeElevatorsCurrentAndTargetFloorsStateToGiven() {
    elevator.addRequest(4);
    assertEquals(0, elevator.getCurrentFloor());
    assertEquals(0, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorLowerThanTargetWhenDoingStepThenPollRequestToCurrent() {
        elevator.update(10);
        elevator.step();
        assertEquals(0, elevator.getCurrentFloor());
        assertEquals(10, elevator.getTargetFloor());
        assertTrue(elevator.getRequests().isEmpty());
    }

    @Test
    void testGivenCurrentElevatorStateFloorHigherThanTargetWhenDoingStepThenPollNewRequest() {
        elevator.update(5);
        for (int i = 0; i < 6; i++) {
            elevator.step();
        }
        elevator.update(3);
        elevator.step();
        assertEquals(5, elevator.getCurrentFloor());
        assertEquals(3, elevator.getTargetFloor());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorHigherThanTargetWhenDoingTwoStepsThenDecreaseCurrentFloorByTwo() {
        elevator.update(5);
        for (int i = 0; i < 6; i++) {
            elevator.step();
        }
        elevator.update(3);
        for (int i = 0; i < 3; i++) {
            elevator.step();
        }
        assertEquals(3, elevator.getCurrentFloor());
        assertEquals(3, elevator.getTargetFloor());
    }
    @Test
    void testGivenCurrentElevatorStateFloorEqualThanTargetWhenDoingTwoStepsEmptyRequests() {
        elevator.update(1);
        elevator.step();
        elevator.step();
        assertEquals(1, elevator.getCurrentFloor());
        assertEquals(1, elevator.getTargetFloor());
        assertTrue(elevator.getRequests().isEmpty());
    }

    @Test
    void testGivenCurrentElevatorStateCurrentFloorEqualsTargetWhenDoingStepStayIdle() {
        elevator.update(0);
        elevator.step();
        assertEquals(0, elevator.getCurrentFloor());
        assertEquals(0, elevator.getTargetFloor());
        assertTrue(elevator.isIdle());
    }

    @Test
    void testGivenCurrentFloorAndTargetFloorNotEqualsAndNoRequestsWhenCheckingIsIdleThenReturnFalse() {
        elevator.update(4);
        elevator.update(3);
        elevator.step();
        assertFalse(elevator.isIdle());
    }

    @Test
    void testGivenFloorLowerThanCurrentAndHigherThanTargetAndMovingUpDirectionWhenCheckIsMovingTowardsThenReturnTrue() {
        elevator.update(10);
        elevator.step();
        assertTrue(elevator.isMovingTowardsFloor(8, 1));
    }

    @Test
    void testGivenFloorHigherThanCurrentAndLowerThanTargetAndMovingDownDirectionWhenCheckIsMovingTowardsThenReturnTrue() {
        elevator.update(10);
        for (int i = 0; i < 10; i++) {
            elevator.step();
        }
        elevator.update(3);
        elevator.step();
        assertTrue(elevator.isMovingTowardsFloor(6, -1));
    }

    @Test
    void testGivenFloorHigherThanCurrentAndLowerThanTargetAndMovingUpDirectionWhenCheckIsMovingTowardsThenReturnFalse() {
        elevator.update(3);
        elevator.step();
        assertFalse(elevator.isMovingTowardsFloor(12, 1));
    }

    @Test
    void testGivenFloorLowerThanCurrentAndLowerThanTargetAndMovingDownDirectionWhenCheckIsMovingTowardsThenReturnFalse() {
        elevator.update(20);
        for (int i = 0; i < 20; i++) {
            elevator.step();
        }
        elevator.update(9);
        assertFalse(elevator.isMovingTowardsFloor(8, -1));
    }


}