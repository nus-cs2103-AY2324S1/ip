package commands;

import java.util.NoSuchElementException;

import client.Rock;
import io.Parser;
import tasks.TaskDeadline;
import tasks.TaskEvent;
import tasks.TaskTodo;
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
 * 
 * @author Alvis Ng (supermii2)
 */
public class CommandTaskCreate extends Command {
    TaskTypes taskType;
    /**
     * Creates a Command object of
     * the specified task type.
     * @param taskType Type of task that the created command should create.
     */
    public CommandTaskCreate(Rock client, TaskTypes taskType) {
        super(client);
        this.taskType = taskType;
    }    
    @Override
    /**
     * Creates task and adds it to the TaskList.
     * @param input Contains data for the Task to be created.
     * @throws IllegalArgumentException Thrown when data is missing or invalid data is given.
     */
    public void accept(Parser input) throws IllegalArgumentException {
        String taskName = input.getDefaultString();
        if (taskName == "") throw new IllegalArgumentException("Name of task cannot be empty!");
        switch (this.taskType) {
            case TODO:
                this.client.taskList.addTask(new TaskTodo(taskName)); 
                this.client.ui.respond("Todo Task added!"); 
                break;
            case DEADLINE:
                try {
                    String deadlineTime = input.getTaggedInput("by");
                    this.client.taskList.addTask(new TaskDeadline(taskName, deadlineTime));
                    this.client.ui.respond("Deadline Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No deadline given. Indicate deadline with tag: /by");
                }
            case EVENT:
                try {
                    String startTime = input.getTaggedInput("from");
                    String endTime = input.getTaggedInput("to");
                    this.client.taskList.addTask(new TaskEvent(taskName, startTime, endTime));
                    this.client.ui.respond("Event Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No start or end time given. Indicate with /from and /to.");
                }                   
        }
        this.client.storage.saveSaveFile();
    }
}
