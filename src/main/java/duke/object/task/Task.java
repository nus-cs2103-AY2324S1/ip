package duke.object.task;

import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

/**
 * Represents a task stored by the program.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param description The user's description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }

    /**
     * Constructs the command needed to create the task.
     * 
     * @param idx The task's index in a given list;
     * @return The string command needed to create the task.
     */
    public String toCommand(int idx) {
        Map<String, Object> indexMap = Map.ofEntries(new SimpleEntry<>("index", idx));
        if (this.isDone) {
            return (new MarkCommand(indexMap)).toString();
        }
        return (new UnmarkCommand(indexMap)).toString();
    }

    /**
     * Returns whether a key is related to the task description.
     * 
     * @param key The key to search for.
     * @return Whether the key is a substring of the task description.
     */
    public boolean isRelated(String key) {
        return this.description.contains(key);
    }

}
