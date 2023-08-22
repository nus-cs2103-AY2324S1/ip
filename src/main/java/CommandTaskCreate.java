import java.util.NoSuchElementException;

enum TaskTypes {
    TODO,
    DEADLINE,
    EVENT,
}
public class CommandTaskCreate extends Command {
    TaskTypes taskType;
    CommandTaskCreate(TaskTypes taskType) {
        super();
        this.taskType = taskType;
    }    
    @Override
    public void accept(Parser input) throws IllegalArgumentException{
        String taskName = input.getDefaultString();
        if (taskName == "") throw new IllegalArgumentException("Name of task cannot be empty!");
        switch (this.taskType) {
            case TODO:
                Rock.taskList.add(new TaskTodo(taskName)); 
                Rock.respond("Todo Task added!"); 
                break;
            case DEADLINE:
                try {
                    String deadlineTime = input.getTaggedInput("by");
                    Rock.taskList.add(new TaskDeadline(taskName, deadlineTime));
                    Rock.respond("Deadline Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No deadline given. Indicate deadline with tag: /by");
                }
            case EVENT:
                try {
                    String startTime = input.getTaggedInput("from");
                    String endTime = input.getTaggedInput("to");
                    Rock.taskList.add(new TaskEvent(taskName, startTime, endTime));
                    Rock.respond("Event Task added!");
                    break;
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("No start or end time given. Indicate with /from and /to.");
                }                   
        }
    }
}
