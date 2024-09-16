package task;

import command.CommandException;
import util.DateTimeUtil;

public class DurationTask extends Task {

    private float durationHours;

    /**
     * The constructor for the DurationTask class
     *
     * @param name The name of the task
     * @param durationHours The duration of the task, in hours
     *
     */
    public DurationTask(String name, float durationHours) {
        super(name);
        this.durationHours = durationHours;
    }

    @Override
    public String toString(){
        return "[Duration]" + super.toString() + " (needs " + this.durationHours + " hours)";
    }
}
