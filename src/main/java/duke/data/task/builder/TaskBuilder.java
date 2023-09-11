package duke.data.task.builder;

import duke.data.task.Task;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

public class TaskBuilder implements Builder<Task> {
    private DeadlineBuilder deadlineBuilder = new DeadlineBuilder();
    private EventBuilder eventBuilder = new EventBuilder();
    private TodoBuilder todoBuilder = new TodoBuilder();
    
    /**
     * Builds a Task object from a string.
     * @param input String input from user in the format "todo <description>" or "deadline <description> /by <by>" or "event <description> /from <start> /to <end>".
     * @return Task object.
     * @throws DukeException If input is invalid.
     */
    @Override
    public Task buildFromString(String input) throws DukeException {
        String type = input.split(" ")[0];
        switch (type) {
            case "todo":
                return todoBuilder.buildFromString(input);
            case "deadline":
                return deadlineBuilder.buildFromString(input);
            case "event":
                return eventBuilder.buildFromString(input);
            default:
                throw new InvalidInputException(
                        "expected format: todo <description> or deadline <description> /by <by> or event <description> /from <start> /to <end>");
        }
    }

}
