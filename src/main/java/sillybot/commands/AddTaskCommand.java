package sillybot.commands;

import java.io.IOException;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.exceptions.IncompleteInputException;
import sillybot.exceptions.InvalidInputException;
import sillybot.tasks.Task;
import sillybot.tasks.TaskList;

/**
 * Represents generic command object to deal with various tasks.
 */
public class AddTaskCommand extends Command {
    private final String taskType;

    /**
     * Creates a AddTaskCommand object.
     *
     * @param taskType The type of task to be added.
     */
    public AddTaskCommand(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Executes the AddTaskCommand object.
     *
     * @return The String representation of the AddTaskCommand object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        String response;

        try {
            Task task = Task.createTask(taskType);
            tasks.addTask(task);
            response = ui.addTaskMessage(task);
            storage.save(tasks);
        } catch (InvalidInputException e) {
            throw new InvalidInputException("I dont understand! " + e);
        } catch (IncompleteInputException e) {
            throw new IncompleteInputException("Incomplete input eh! " + e);
        } catch (IOException e) {
            response = ui.showSaveError();
        }

        return response;
    }
}
