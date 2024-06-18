# Elevator System Documentation

### Contents:
1. Project Structure
2. Usage
3. Elevator Algorithm
4. How To Run

## 1. Project Structure

## Package:  `pl.hubertmaka.system`

### Class:  `Elevator`

The  `Elevator`  class simulates an individual elevator's behavior and state within a building. This is a package range class which is used only by `ElevatorSystem` API class.

#### Fields:

-   `private static final Logger logger`: Logger for the  `Elevator`  class.
-   `private final int elevatorID`: Unique identifier for the elevator.
-   `private int currentFloor`: The current floor where the elevator is located. Initialized by 0.
-   `private int targetFloor`: The floor where the elevator is heading. Initialized by 0.
-   `private LinkedList<Integer> requests`: List of requested floors. Acts like FIFO.

#### Constructor:

-   `Elevator(int elevatorID)`: Initializes the elevator with a specific ID. Logs the initialization and sets the current and target floor to 0.

#### Methods:

-   `private void checkElevatorIdCorrectness(int elevatorID)`: Checks if the elevator ID is valid (greater than 0).
-   `int getElevatorID()`: Returns the elevator's ID (elevatorID getter).
-   `int getCurrentFloor()`: Returns the current floor (elevatorCurrentFloor getter).
-   `int getTargetFloor()`: Returns the target floor (elevatorTargetFloor getter).
-   `LinkedList<Integer> getRequests()`: Returns the list of floor requests.
-   `void addRequest(int floor)`: Adds a floor to the request list if it's not already present or not the current target floor.
-   `boolean isIdle()`: Checks if the elevator is idle (no requests and current floor equals target floor).
-   `boolean isMovingTowardsFloor(int floor, int direction)`: Checks if the elevator is moving towards a specific floor in a given direction.
-   `void update(int targetFloor)`: Adds a new target floor to the request list.
-   `void step()`: Moves the elevator one step towards (one simulation step) its target floor and processes the next request if needed.
-   `private void changeFloor()`: Changes the current floor by one step towards the target floor.
-   `private void pollNewRequest()`: Sets the next target floor from the request list if the elevator has reached its current target floor.

### Class:  `ElevatorSystem`

The  `ElevatorSystem`  class manages multiple elevators in a building, handles requests, and updates the state of each elevator.

#### Fields:

-   `private static final Logger logger`: Logger for the  `ElevatorSystem`  class.
-   `private final static int MAX_ELEVATORS = 16`: Maximum number of elevators allowed.
-   `private final List<Elevator> elevators`: List of all elevators in the system.

#### Constructor:

-   `ElevatorSystem(int elevatorsNumber)`: Initializes the system with a specified number of elevators. Logs the creation of each elevator.

#### Methods:

-   `private boolean checkElevatorsNumberCorrectness(int elevatorsNumber)`: Checks if the number of elevators is within valid range.
-   `private void createElevators(int elevatorsNumber)`: Creates the specified number of elevators.
-   `public List<Integer> getElevators()`: Returns a list of elevator IDs.
-   `public List<ArrayList<Integer>> status()`: Returns the status of all elevators (ID, current floor, target floor).
-   `public void pickup(int floor, int direction)`: Handles a pickup request by finding the most suitable (algorithm description in section 3) elevator.
-   `private Elevator findClosestElevator(int floor)`: Finds the closest idle elevator to the specified floor.
-   `public void update(int elevatorId, int targetFloor)`: Updates the target floor for a specific elevator. Simulates person in elevator who is choosing destination floor.
-   `public void step()`: Moves all elevators one step towards their respective target floors.

## Package:  `pl.hubertmaka`

### Class:  `ElevatorSystemConsole`

The  `ElevatorSystemConsole`  class provides a console interface for interacting with the elevator system.

#### Fields:

-   `private static final Logger logger`: Logger for the  `ElevatorSystemConsole`  class.

#### Methods:

-   `public static void main(String[] args)`: Entry point of the application. Runs the console interface.
-   `private static void runApp()`: Main loop for the console application, handling user input and commands.
-   `private static void pickupElevator(Scanner scanner, ElevatorSystem elevatorSystem)`: Handles the "pickup" command to request an elevator.
-   `private static void updateElevatorTargetFloor(Scanner scanner, ElevatorSystem elevatorSystem)`: Handles the "update" command to set the target floor of an elevator.
-   `private static void printStatus(ElevatorSystem elevatorSystem)`: Prints the current status of all elevators.
-   `private static void runSimulationStep(ElevatorSystem elevatorSystem)`: Runs a simulation step, moving elevators towards their targets.
-   `private static void exitApplication()`: Exits the application.
-   `private static void printInvalidCommand()`: Prints a message for invalid commands.
-   `private static void printInfo()`: Prints the available commands.

## 2. Usage

### Running the Application

1.  Compile and run the  `ElevatorSystemConsole`  class. More info in section 4.
2.  Enter the number of elevators (maximum 16).
3.  Use the following commands to interact with the system:
    -   `pickup`: Request an elevator to a specific floor and direction.
    -   `update`: Update the target floor for a specific elevator.
    -   `status`: Print the status of all elevators.
    -   `step`: Run a simulation step.
    -   `exit`: Exit the application.

## 3. Elevator Algorithm
### 1. Picking an Elevator
Algorithm picks an elevator by at the beginning looking for elevators which are at the same floor as user who want to summon them. If there is no elevator on this floor then algorithm are looking for elevators which are moving towards this floor and in the direction which user defined. The closest one is choosen. If no elevator is still selected then algorithm seeks for the closest one by the metric of the number of floors separating user and elevator.

### 2. Dealing with Requests
It is standard `FIFO (First-In-First-Out)` queue. If any user enters the elevator and press the button then it will add user request to de queue. This algorithm can be expanded to sorting the queue and updating elevator target floor by floors which are on the way to the original floor.

## 4. How To Run
You can run it in two ways:
1. By pulling `docker image` from `dockerhub` (Recommended).
2. By building it and running locally.

### 1. Run with Docker:
In running CLI:
1. Pull repository:
```bash
docker pull hubertmaka/elevator-system:latest
```
2. Run container:
```
docker run -it --rm --name elevator-system-container hubertmaka/elevator-system:latest
```

### 2. Run Locally:
In Dunning CLI:
1. Clone repository:
```bash
git clone https://github.com/hubertmaka/Elevator-System.git
```
2. Go into:
```bash
cd ./Elevator-System
```
3. Build:
```bash
mvn clean install
```
4. Run:
```bash
java -jar target/Elevator-System-1.0-SNAPSHOT-jar-with-dependencies.jar
```


