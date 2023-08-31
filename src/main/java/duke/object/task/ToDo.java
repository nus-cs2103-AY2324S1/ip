package duke.object.task;

import duke.command.task.ToDoCommand;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Task without any date requirements.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * 
     * @param description The user's description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toCommand(int idx) {
        return (new ToDoCommand(Map.ofEntries(
                new SimpleEntry<>("description", this.description)))).toString()
                + "\n" + super.toCommand(idx) + "\n";
    }

}
