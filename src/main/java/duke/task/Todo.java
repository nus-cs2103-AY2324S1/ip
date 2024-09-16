package duke.task;

import java.util.Map;

import duke.exception.DukeException;

/** Represents a Todo task */
public class Todo extends Task {
    /**
     * Returns a Todo
     *
     * @param description the description of task
     * @param isMarked if the task is marked
     * @return the created Todo
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked, 'T');
    }

    @Override
    public void edit(Map<String, String> arguments) {
        throw new DukeException("todo cannot be snoozed");
    }
}
