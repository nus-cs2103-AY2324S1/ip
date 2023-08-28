package rat.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import rat.tasks.RatTaskManager;

public abstract class RatCommand {

    protected final RatTaskManager ratTaskManager;

    protected RatCommand(RatTaskManager ratTaskManager) {
        this.ratTaskManager = ratTaskManager;
    }

    public abstract void execute();

    protected static boolean validateTime(String time) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.parse(time.trim());
        return true;
    }

}
