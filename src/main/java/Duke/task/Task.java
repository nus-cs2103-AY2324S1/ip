package Duke.task;

import Duke.exception.DukeException;
import Duke.exception.EmptyTaskDescException;
import Duke.exception.NoCommandFoundException;

/**
 * Represents a task with a name and completion status.
 */
public abstract class Task {
    final private String name;
    private boolean completed = false;


    /**
     * Parses a task from a line of text.
     *
     * @param line The line of text containing task information.
     * @return A Task object parsed from the input line.
     * @throws DukeException If there's an issue with parsing the task.
     */
    public static Task parse(String line)
            throws DukeException {
        String[] components = line.split(":", 2);
        TaskType taskType = components[0].equals("todo") ? TaskType.TODO
                          : components[0].equals("deadline") ? TaskType.DEADLINE
                          : TaskType.EVENT;
        String content = components[1];
        switch (taskType) {
        case TODO:
            return Todo.unpackSaveFormat(content);
        case DEADLINE:
            return Deadline.unpackSaveFormat(content);
        case EVENT:
            return Event.unpackSaveFormat(content);
        default:
            throw new NoCommandFoundException(taskType.name());
        }
    }

    public boolean containsString(String content) {
        return name.contains(content);
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    public Task(String name) throws EmptyTaskDescException {
        if(name.isBlank()) {
            throw new EmptyTaskDescException("Name cannot be blank.");
        }
        this.name = name;
    }

    /**
     * Creates a Task object based on the provided content and task type.
     *
     * @param task The content of the task.
     * @param taskType The type of the task.
     * @return A Task object of the specified type.
     * @throws DukeException If there's an issue with creating the task.
     */
    public static Task of(String task, TaskType taskType)
            throws DukeException {
        switch (taskType) {
            case TODO:
                return new Todo(task);
            case DEADLINE:
                return new Deadline(task);
            case EVENT:
                return new Event(task);
            default:
                throw new NoCommandFoundException(taskType.name());
        }
    }
    public void setCompleted(){
        completed = true;
    }
    public void setUncompleted(){
        completed = false;
    }
    @Override
    public String toString() {

        return completed ? "[X] " + name : "[ ] " + name;

    }

    public String toSaveFormat(){
        return name + "|" + completed;
    }
}
