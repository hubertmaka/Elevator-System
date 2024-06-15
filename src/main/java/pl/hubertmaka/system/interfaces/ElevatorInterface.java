package pl.hubertmaka.system.interfaces;

public interface ElevatorInterface {
    public void update(int currentFloor, int targetFloor);
    public void step();
}
