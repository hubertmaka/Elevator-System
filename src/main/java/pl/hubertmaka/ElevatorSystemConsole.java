package pl.hubertmaka;

import pl.hubertmaka.system.ElevatorSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ElevatorSystemConsole {

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elevators (MAX IS 16): ");
        int elevatorsNumber = scanner.nextInt();
        scanner.nextLine();

        ElevatorSystem elevatorSystem = new ElevatorSystem(elevatorsNumber);

        while (true) {
            printInfo();
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "pickup" -> {
                    pickupElevator(scanner, elevatorSystem);
                }
                case "update" -> {
                    updateElevatorTargetFloor(scanner, elevatorSystem);
                }
                case "status" -> {
                    printStatus(elevatorSystem);
                }
                case "step" -> {
                    runSimulationStep(elevatorSystem);
                }
                case "exit" -> {
                    exitApplication();
                }
                default -> printInvalidCommand();
            }

        }
    }

    private static void pickupElevator(Scanner scanner, ElevatorSystem elevatorSystem) {
        System.out.println("Enter floor and direction (-1 for down, 1 for up):");
        int floor = scanner.nextInt();
        int direction = scanner.nextInt();
        scanner.nextLine();
        elevatorSystem.pickup(floor, direction);
    }

    private static void updateElevatorTargetFloor(Scanner scanner, ElevatorSystem elevatorSystem) {
        System.out.println("Enter elevator ID and target floor:");
        int elevatorID = scanner.nextInt();
        int targetFloor = scanner.nextInt();
        scanner.nextLine();
        elevatorSystem.update(elevatorID, targetFloor);
    }

    private static void printStatus(ElevatorSystem elevatorSystem) {
        System.out.println("Printing Status...");
        List<ArrayList<Integer>> elevatorsStatus = elevatorSystem.status();
        for (ArrayList<Integer> elevator: elevatorsStatus) {
            System.out.println("Elevator ID: " + elevator.get(0));
            System.out.println("\tCurrent floor: " + elevator.get(1));
            System.out.println("\tTarget floor: " + elevator.get(2));
        }
    }
    private static void runSimulationStep(ElevatorSystem elevatorSystem) {
        System.out.println("Running simulation step...");
        elevatorSystem.step();
    }

    private static void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    private static void printInvalidCommand() {
        System.out.println("Invalid command. Please try again...");
    }

    private static void printInfo() {
        System.out.println("Chose an option: ");
        System.out.println("\tpickup - to pickup the elevator");
        System.out.println("\tupdate - to define target floor when you in elevator");
        System.out.println("\tstatus - to get status of elevators");
        System.out.println("\tstep - to run simulation step");
        System.out.println("\texit - to exit from application");
    }
}
