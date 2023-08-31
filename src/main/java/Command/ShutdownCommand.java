package Command;
import Duke.Duke;

public class ShutdownCommand implements Commandable {
    public void execute(Duke duke) {
        duke.shutdownDuke();
    }

}
