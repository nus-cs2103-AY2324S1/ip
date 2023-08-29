package duke.object.task;

import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " "; // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String toCommand(int idx) {
        Map<String, Object> indexMap = Map.ofEntries(new SimpleEntry<>("index", idx));
        if (this.isDone) {
            return (new MarkCommand(indexMap)).toString();
        }
        return (new UnmarkCommand(indexMap)).toString();
    }

}
