package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;
import ducky.task.DeadlineTask;
import ducky.task.EventTask;
import ducky.task.TaskType;
import ducky.task.TodoTask;
import ducky.util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command that adds a task to Ducky's task list.
 */
public class AddTaskCommand extends Command {

    private final TaskType type;
    private final String[] args;

    /**
     * Constructs a command that adds the specified type of task with additional arguments.
     * @param type Type of task.
     * @param args Arguments specific to the given type of task.
     */
    public AddTaskCommand(TaskType type, String... args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Adds the task to Ducky's task list, saves the state to file system,
     * then reflects changes to user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param ui       UserInterface of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException {
        switch (this.type) {
        case TODO:
            TodoTask newTodo = new TodoTask(this.args[0]);
            taskList.addTask(newTodo);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newTodo.toString(),
                    taskList.getListLengthStatus()
                    );
            break;
        case DEADLINE:
            LocalDate deadline;
            try {
                deadline = Parser.parseDate(this.args[1]);
            } catch (DateTimeParseException e) {
                throw new DuckyInvalidCommandFormatException(
                        "Your deadline should be in yyyy-mm-dd format."
                );
            }
            DeadlineTask newDeadline = new DeadlineTask(this.args[0], deadline);
            taskList.addTask(newDeadline);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newDeadline.toString(),
                    taskList.getListLengthStatus()
            );
            break;
        case EVENT:
            EventTask newEvent = new EventTask(this.args[0], this.args[1], this.args[2]);
            taskList.addTask(newEvent);
            storage.save(taskList);
            ui.showMessagePerLine(
                    "Okay! I've added this task:",
                    newEvent.toString(),
                    taskList.getListLengthStatus()
            );
            break;
        }
        return null;
    }
}
