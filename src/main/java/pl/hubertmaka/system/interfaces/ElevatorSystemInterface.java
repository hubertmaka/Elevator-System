package pl.hubertmaka.system.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface ElevatorSystemInterface {
    public List<ArrayList<Integer>> status();
    public void pickup(int floor, int destination);
    public void update(int elevatorId, int targetFloor);
    public void step();

}
