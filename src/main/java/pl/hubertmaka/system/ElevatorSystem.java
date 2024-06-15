package pl.hubertmaka.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.hubertmaka.system.interfaces.ElevatorInterface;
import pl.hubertmaka.system.interfaces.ElevatorSystemInterface;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem  implements ElevatorSystemInterface {
    private static final Logger logger = LogManager.getLogger(Elevator.class);
    private final static int MAX_ELEVATORS = 16;
    private List<Elevator> elevators;


    public ElevatorSystem(int elevatorsNumber) {
        createElevators(elevatorsNumber);

    }

    private boolean checkElevatorsNumberCorrectness(int elevatorsNumber) {
        if (elevatorsNumber <= 0 || elevatorsNumber > MAX_ELEVATORS) {
            logger.warn("TRY TO SET ILLEGAL ELEVATORS NUMBER");
            throw new IllegalArgumentException(
                    "Elevators number must be greater than 0 and lesser than"
                            + MAX_ELEVATORS + "but is: " + elevatorsNumber
            );
        }
        return true;
    }

    private void createElevators(int elevatorsNumber) {
        if (checkElevatorsNumberCorrectness(elevatorsNumber)) {
            for(int id = 0; id < elevatorsNumber; id++) {
                logger.info("CREATING ELEVATOR WITH ID " + id);
                this.elevators.add(new Elevator(id));
            }
        }
    }

    @Override
    public List<ArrayList<Integer>> status() {
        return null;
    }

    @Override
    public void pickup() {

    }
}
