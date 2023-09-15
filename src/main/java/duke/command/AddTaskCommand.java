package duke.command;

import java.util.Map;

import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** Represents an add task instruction. Task type is generic, can be Todo, Deadline etc */
public class AddTaskCommand extends Command {
    private String type;
    private String name;
    private Map<String, String> arguments;

    /**
     * Returns an AddTaskCommand
     *
     * @param out Printer to print output to terminal
     * @param taskList TaskList to add task to
     * @param savefile File to save tasks to
     * @param type Type of task
     * @param name Description of task
     * @param arguments Represents the different possible arguments like /by /from etc
     * @return Add task command
     */
    public AddTaskCommand(
        Printer out,
        TaskList taskList,
        FileIO savefile,
        String type,
        String name,
        Map<String, String> arguments) {
        super(out, taskList, savefile);
        this.type = type;
        this.name = name;
        this.arguments = arguments;
    }

    /** The actions to take during execute of command. Creates a task and adds it to tasklist. */
    @Override
    protected void action() {
        Task task = Task.createTask(type, name, arguments);
        taskList.addTask(task);
        out.print("Got it. I've added this task:", task.toString(), taskList.getNumberOfTasks());
        save();
    }
}
