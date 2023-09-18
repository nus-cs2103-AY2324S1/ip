package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;

/**
 * Represents a generic command (add task, delete task etc.). Command is built and called during
 * execute
 */
public abstract class Command {
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DELETE = "delete";
    public static final String FIND = "find";
    public static final String SNOOZE = "snooze";

    protected Printer out;
    protected TaskList taskList;
    private FileIO savefile;

    private boolean shouldSaveAfterAction = false;

    /**
     * Returns a Command
     *
     * @param out Printer to print output
     * @param taskList TaskList is modify
     * @param savefile File to write tasklist to
     * @return Command object
     */
    public Command(Printer out, TaskList taskList, FileIO savefile) {
        this.out = out;
        this.taskList = taskList;
        this.savefile = savefile;
    }

    /**
     * Saves after calling action(). Note that save always occur after action() and not
     * when save is called
     */
    public final void save() {
        this.shouldSaveAfterAction = true;
    }

    /**
     * Executes the code logic, catches exceptions to print to output. Modify this behaviour by
     * overriding action().
     */
    public final void execute() {
        try {
            action();
        } catch (DukeException e) {
            out.print(e);
            return;
        } catch (DukeSideEffectException e) {
            out.print(e);
        }

        if (shouldSaveAfterAction) {
            try {
                savefile.write(taskList.toString());
            } catch (IOException e) {
                out.print(
                    new DukeSideEffectException(
                    String.format("Unable to write to savefile %s", savefile.getFilename())));
            }
        }

    }

    /**
     * Represents the business logic of command. Override this function to set the behaviour of a
     * command.
     */
    protected abstract void action();
}
