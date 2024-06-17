package pl.hubertmaka.system.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface ElevatorSystemInterface {
    public List<ArrayList<Integer>> status();
    public void pickup();
    public void update(int elevatorId, int currentFloor, int targetFloor);
    public void step();

}
