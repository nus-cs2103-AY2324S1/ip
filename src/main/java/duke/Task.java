package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the right representation for whether the task is done
     *
     * @return string X if done and white space if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Change boolean of isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Change boolean of isDone to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Reutrn the string representation of boolean isDone
     *
     * @return string [X] for done and [ ] for not done
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Return the right string representation for saving to file
     *
     * @return string 1 for done and 0 for not done
     */
    public String status() {
        String done = isDone ? "1" : "0";
        return done + "|" + description;
    }

    public abstract String save();

    /**
     * Return the task by reading the detail of the task from the file
     *
     * @param taskString the description of the task from the file
     * @return the task fitting the description
     */
    public static Task fromString(String taskString) {
        String[] parts = taskString.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Task task = new ToDo(description);
            if (isDone) {
                task.markAsDone();
            }
            return task;
        case "D":
            String byString = parts[3].trim(); // Extract deadline
            if (byString.contains(" ")) {
                String[] part = byString.split(" ");
                String dateString = part[0];
                String timeString = part[1];
                LocalDate d1;
                LocalTime t1;

                d1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                t1 = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
                Task deadline = new Deadline(description, d1, t1);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;

            } else {
                LocalDate byDate = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Task deadline = new Deadline(description, byDate, null);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            }

        case "E":
            String fromString = parts[3]; // Extract start date
            LocalDate fromDate = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String toString = parts[4]; // Extract end date
            LocalDate toDate = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Task event = new Events(description, fromDate, toDate);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}
