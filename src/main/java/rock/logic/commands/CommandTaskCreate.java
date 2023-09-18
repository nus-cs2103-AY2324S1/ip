package rock.logic.commands;

import java.util.NoSuchElementException;

import rock.client.Rock;
import rock.logic.io.Parser;
import rock.tasks.*;

/**
 * Representation of possible types
 * of tasks that can be created.
 */
enum TaskTypes {
    TODO,
    DEADLINE,
    EVENT,
}
/**
 * Representation of command used
 * to create a new task.
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskCreate extends Command {
    /** Represents the type of task this Command Object creates*/
    private TaskTypes taskType;
    /**
     * Creates a Command object of
     * the specified task type.
     * @param taskType Type of task that the created command should create.
     */
    public CommandTaskCreate(Rock client, TaskTypes taskType) {
        super(client);
        this.taskType = taskType;
    }
    /**
     * Creates task and adds it to the TaskList.
     * @param input Contains data for the Task to be created.
     * @throws IllegalArgumentException Thrown when data is missing or invalid data is given.
     */
    @Override
    public String apply(Parser input) throws IllegalArgumentException {
        String taskName = input.getDefaultString();
        if (taskName == "") {
            throw new IllegalArgumentException("Name of task cannot be empty!");
        }
        Task task;
        TaskList taskList = client.getTaskList();
        switch (this.taskType) {
        case TODO:
            task = new TaskTodo(taskName);
            if (taskList.isPresent(task)) {
                return ("Task already exists!");
            } else {
                taskList.addTask(task);
                this.client.saveFile();
                return ("Todo Task added!");
            }
        case DEADLINE:
            try {
                String deadlineTime = input.getTaggedInput("by");
                task = new TaskDeadline(taskName, deadlineTime);
                if (taskList.isPresent(task)) {
                    return ("Task already exists!");
                } else {
                    taskList.addTask(task);
                    this.client.saveFile();
                    return ("Deadline Task added!");
                }
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("No deadline given. Indicate deadline with tag: /by");
            }
        case EVENT:
            try {
                String startTime = input.getTaggedInput("from");
                String endTime = input.getTaggedInput("to");
                task = new TaskEvent(taskName, startTime, endTime);
                if (taskList.isPresent(task)) {
                    return ("Task already exists!");
                } else {
                    taskList.addTask(task);
                    this.client.saveFile();
                    return ("Event Task added!");
                }
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("No start or end time given. Indicate with /from and /to.");
            }
        default:
            // SHOULD NEVER REACH HERE
            throw new IllegalArgumentException("Invalid Task Type given");
        }
    }
}
