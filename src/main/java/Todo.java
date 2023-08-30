import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Todo class represents a simple task without any additional details.
 * It extends the Task class and inherits its description field.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method to format the Todo task's details.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void addTodoTask(ArrayList<Task> store, String[] commandTask) {
        Task curr = new Todo(commandTask[1]);
        store.add(curr);

        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");

    }

    public void addtoStore() throws IOException {
        FileWriter fw = new FileWriter("./data/sae.txt", true);
        String completion = isDone ? "1" : "0";
        // Check if the file is empty, if not, add a new line before writing
        File file = new File("./data/sae.txt");
        if (file.length() > 0) {
            fw.write("\n");
        }
        fw.write("T | " + completion + " | " + description);
        fw.close();
    }

    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";
        return String.format("%s | %s | %s" , "T", completionStatus, description.trim());
    }
}