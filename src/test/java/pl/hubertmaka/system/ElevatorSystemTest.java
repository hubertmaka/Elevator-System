package pl.hubertmaka.system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorSystemTest {
    private ElevatorSystem elevatorSystem;
    @BeforeEach
    void setUp() {
        elevatorSystem = new ElevatorSystem(3);
    }

    @Test
    void testGivenThreeElevatorsToCreateWhenCreatingElevatorsThenCreateThreeElevators() {
        List<Elevator> elevators = new ArrayList<>();
        elevators.add(new Elevator(1));
        elevators.add(new Elevator(2));
        elevators.add(new Elevator(3));
        assertEquals(elevators.size(), elevatorSystem.getElevators().size());
    }

    @Test
    void testGivenNegativeElevatorsToCreateWhenCreatingElevatorsThenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ElevatorSystem(-1)
        );
    }

    @Test
    void testGivenZeroElevatorsToCreateWhenCreatingElevatorsThenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ElevatorSystem(0)
        );
    }

    @Test
    void testGivenOverMaxValueElevatorsToCreateWhenCreatingElevatorsThenThrowIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ElevatorSystem(17)
        );
    }

    @Test
    void testGivenTargetFloorNr7WhenAssignToId0ThenUpdateIdStatusOfElevatorNr7() {
        elevatorSystem.update(0, 7);
        elevatorSystem.step();
        assertEquals(7, (int) elevatorSystem.status().getFirst().get(2));
    }

    @Test
    void testGivenPickupFromFloor10WhenAllElevatorsOnFloor0ThenPickupFirst() {
        elevatorSystem.pickup(7, 1);
        elevatorSystem.step();
        assertEquals(0, elevatorSystem.status().getFirst().getFirst());
        assertEquals(0, elevatorSystem.status().getFirst().get(1));
        assertEquals(7, elevatorSystem.status().getFirst().get(2));
    }

    @Test
    void testGivenPickupFromFloor10WhenOneElevatorOnFloor11ThenPickupFloor11() {
        elevatorSystem.update(1, 10);
        for (int i = 0; i < 10; i++) {
            elevatorSystem.step();
        }
        elevatorSystem.pickup(10, -1);
        elevatorSystem.step();
        assertEquals(1, elevatorSystem.status().get(1).getFirst());
        assertEquals(10, elevatorSystem.status().get(1).get(1));
        assertEquals(10, elevatorSystem.status().get(1).get(2));
    }
    @Test
    void testGivenFourFloorsWhenPickupFromSeventhFloorThenChooseNearestElevatorWithId2() {
        elevatorSystem.update(0, 0);
        elevatorSystem.update(1, 5);
        elevatorSystem.update(2, 10);

        elevatorSystem.pickup(7, 1);

        assertEquals(2, elevatorSystem.getElevators().get(2));
    }

}