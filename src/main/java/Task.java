import java.io.*;
import java.util.List;
//The Task class is in charge of creating task objects
//which can be of different types: ToDos, Deadlines and Events
abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    private static final long serialVersionUID = -7108460826726050219L;
    //included serialVersionUID indicated as the Task implements Serializable Interface
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getTask() {
        return this.description;
    }

    public String taskString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = str + this.description;
        return output;
    }
    public static void listTasks(List<Task> tasks) {
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println();
            System.out.print("\t" + i + "." + task.taskString());
            i++;
        }
    }
}