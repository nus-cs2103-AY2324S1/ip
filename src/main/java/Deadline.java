import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and adds a 'by' field to store the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public void addDeadlineTask(ArrayList<Task> store, String[] commandTask) throws InvalidDateTimeFormatException {
        String[] parts = commandTask[1].split("/by");
        String description = parts[0].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        try {
            LocalDateTime by = LocalDateTime.parse(parts[1].trim(), formatter);

            Task curr = new Deadline(description, by);
            store.add(curr);
            System.out.println("Got it. I've added this task:");
            System.out.println(curr.toString());
            System.out.println("Now you have " + store.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("Invalid date and time format");
        }
    }

    public void addtoStore() throws IOException {
        FileWriter fw = new FileWriter("./data/sae.txt", true);
        String completion = isDone ? "1" : "0";
        File file = new File("./data/sae.txt");
        if (file.length() > 0) {
            fw.write("\n");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        String formattedBy = by.format(formatter);

        fw.write("D | " + completion + " | " + description + " | " + formattedBy);
        fw.close();
    }

    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        String formattedBy = by.format(formatter);

        return String.format("%s | %s | %s | %s", "D", completionStatus, description.trim(), formattedBy);
    }
}
