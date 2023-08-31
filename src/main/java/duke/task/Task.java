package duke.task;
import java.util.ArrayList;
import duke.DukeException;

public class Task {

    private String description;
    private boolean isDone;
    public static String line = "___________________________________\n";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void readListFromFile(String[] arr, ArrayList<Task> list) throws DukeException {
        if (arr.length != 3) {
            throw new DukeException("Uh Oh! There seems to be a problem with the file!\n" +
                    "Some of the tasks may be gone! Sorry!!\n");
        }

        String type = arr[0].strip();
        String description = arr[2].strip();
        String isMarked = arr[1].strip();
        if (type.equals("T")) {
            ToDo.addSavedTodo(description, list, isMarked);
        } else if (type.equals("D")) {
            Deadline.addSavedDeadline(description, list, isMarked);
        } else if (type.equals("E")) {
            Event.addSavedEvent(description, list, isMarked);
        }

    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String getType() { return "Task"; }

    public void markFromRead(String isMarked) {
        if (isMarked.equals("1")) this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    public int getStatus() { return (isDone ? 1 : 0); }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toStringFile() {
        return getStatus() + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
