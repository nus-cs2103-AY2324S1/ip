import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public static Task createTaskFromEntry(String entry) {
        Task task;
        String[] splitEntry = entry.split("\\|"); // Split by "|"
        String type = splitEntry[0].trim();

        // Create task according to type
        if (type.equals("T")) {
            task = new ToDo(splitEntry[2].trim());
        } else if (type.equals("D")) {
            String parsedBy = splitEntry[3].trim(); // in MMM d yyyy
            LocalDate byDate = LocalDate.parse(parsedBy, DateTimeFormatter.ofPattern("MMM d yyyy"));
            String by = byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // in yyyy-mm-dd

            task = new Deadline(splitEntry[2].trim(), by);
        } else if (type.equals("E")) {
            task = new Event(splitEntry[2].trim(), splitEntry[3].trim(), splitEntry[4].trim());
        } else {
            return null;
        }

        boolean isDone = splitEntry[1].trim() == "1";
        // Check if task is done
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }

    public abstract String toTaskListEntry();
}
