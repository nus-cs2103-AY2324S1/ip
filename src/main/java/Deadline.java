import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and adds a 'by' field to store the deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString method to format the Deadline task's details.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public void addDeadlineTask(ArrayList<Task> store, String[] commandTask) {
        String[] parts = commandTask[1].split("/by");
        Task curr = new Deadline(parts[0].trim(), parts[1].trim());
        store.add(curr);
        System.out.println("Got it. I've added this task:");
        System.out.println(curr.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public void addtoStore() throws IOException {
        FileWriter fw = new FileWriter("./data/sae.txt", true);
        String completion = isDone ? "1" : "0";
        File file = new File("./data/sae.txt");
        if (file.length() > 0) {
            fw.write("\n");
        }
        fw.write("D | " + completion + " | " + description + " | " + by);
        fw.close();
    }

    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";
        return String.format("%s | %s | %s | %s ", "D", completionStatus, description.trim(), by.trim());
    }
}
