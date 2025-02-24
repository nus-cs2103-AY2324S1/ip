package dot.commands;

import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.Event;
import dot.tasks.Task;
import dot.tasks.TaskList;

/**
 * Command to create an event.
 */
public class EventCommand extends Command implements Undoable {

    private final String description;
    private final String start;
    private final String end;
    private final TaskList dotTaskList;

    /**
     * For the undo method.
     */
    private int oneBasedInsertIndex;

    /**
     * Constructor for EventCommand.
     *
     * @param description This is the description of the event.
     * @param start       This is the description of datetime of the start of the event.
     * @param end         This is the description of datetime of the end of the event.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     */
    public EventCommand(String description, String start, String end,
                        TaskList dotTaskList) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Creates and inserts the Event into dotTaskList.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        Task newEventTask = new Event(this.description, this.start, this.end);
        oneBasedInsertIndex = dotTaskList.addTask(newEventTask, handleDotOutput);
        dotTaskList.saveTaskListToStorage();
    }

    /**
     * Undoes the creation of Event.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void undo(Consumer<String> handleDotOutput) throws DotException {
        Command deleteCommand = new DeleteCommand(oneBasedInsertIndex, dotTaskList);
        deleteCommand.execute(handleDotOutput);
    }

    /**
     * {@inheritDoc}
     *
     * @return true to indicate that this is an Undoable Command.
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

}
