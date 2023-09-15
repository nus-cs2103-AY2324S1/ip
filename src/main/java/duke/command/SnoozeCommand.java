package duke.command;

import java.util.Map;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** SnoozeCommand edits the time arguments for tasks */
public class SnoozeCommand extends Command {
    private String index;
    private Map<String, String> arguments;

    /**
     * Returns a SnoozeCommand
     *
     * @param out Printer to print output
     * @param taskList TaskList to edit
     * @param savefile File to save tasks to
     * @param index string representing number of task to edit
     * @param arguments arguments to edit
     * @return SnoozeCommand
     */
    public SnoozeCommand(Printer out, TaskList taskList, FileIO savefile, String index, Map<String, String> arguments) {
        super(out, taskList, savefile);
        this.index = index;
        this.arguments = arguments;
    }

    /**
     * The actions to take during the command
     *
     * @throws ArgumentMustBeNumException when first argument is not a number
     */
    @Override
    public void action() {
        Task task;
        try {
            task = taskList.getTask(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            throw new ArgumentMustBeNumException(SNOOZE);
        }

        task.edit(arguments);

        out.print("Ok! I've edited this task:", task.toString());
        save();
    }
}
