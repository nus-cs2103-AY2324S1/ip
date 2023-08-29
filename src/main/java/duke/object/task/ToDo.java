package duke.object.task;

import duke.command.task.ToDoCommand;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String toCommand(int idx) {
        return (new ToDoCommand(Map.ofEntries(new SimpleEntry<>("description", this.description)))).toString()
                + "\n" + super.toCommand(idx) + "\n";
    }

}
