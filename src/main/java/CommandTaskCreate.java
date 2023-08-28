import java.util.NoSuchElementException;
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
    CommandTaskCreate(TaskTypes taskType) {
        super();
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
                Rock.taskList.addTask(new TaskTodo(taskName)); 
                Rock.respond("Todo Task added!"); 
                break;
            case DEADLINE:
                try {
                    String deadlineTime = input.getTaggedInput("by");
                    Rock.taskList.addTask(new TaskDeadline(taskName, deadlineTime));
                    Rock.respond("Deadline Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No deadline given. Indicate deadline with tag: /by");
                }
            case EVENT:
                try {
                    String startTime = input.getTaggedInput("from");
                    String endTime = input.getTaggedInput("to");
                    Rock.taskList.addTask(new TaskEvent(taskName, startTime, endTime));
                    Rock.respond("Event Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No start or end time given. Indicate with /from and /to.");
                }                   
        }
        Save.saveSaveFile();
    }
}
