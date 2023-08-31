package Duke.task;

import Duke.exception.EmptyTaskDescException;

/**
 * Represents a to-do task that has a name.
 * Extends the {@link Task} class.
 */
public class Todo extends Task{
    public Todo(String task) throws EmptyTaskDescException {
        super(task);
    }
    public static Todo ParseContent(String content) throws EmptyTaskDescException {
        String[] components = content.split("\\|", 2);
        Todo task = new Todo(components[0]);
        if(components[1].equals("X"))
            task.SetCompleted();
        else
            task.SetUncompleted();
        return task;
    }
    public String toString() {
        return "[T]" + super.toString();
    }
    public String toSaveFormat(){
        return "todo:" + super.toSaveFormat();
    }
}
