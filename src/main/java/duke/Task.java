package duke;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public abstract class Task {
    public enum TaskPriority {
        HIGH,
        NORMAL,
        LOW,
        UNASSIGNED;
    }
    protected String description;
    protected boolean isDone;

    protected TaskPriority priority;
    public Task(String description) {
        assert description != null : "Description cannot be null";
        this.description = description;
        this.isDone = false;
        this.priority = TaskPriority.UNASSIGNED;
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

    public String getPriorityStatus() {
        if (priority == TaskPriority.HIGH) {
            return "High";
        } else if (priority == TaskPriority.NORMAL) {
            return "Mid";
        } else if (priority == TaskPriority.LOW) {
            return "Low";
        } else if (priority == TaskPriority.UNASSIGNED) {
            return "None";
        } else {
            return " ";
        }
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
        return "[" + getStatusIcon() + "] " + "[" + getPriorityStatus() + "] " + description;
    }

    /**
     * Return the right string representation for saving to file
     *
     * @return string 1 for done and 0 for not done
     */
    public String status() {
        String done = isDone ? "1" : "0";
        String priorityStatus = priority == TaskPriority.HIGH ? "3"
                : priority == TaskPriority.NORMAL ? "2" : priority == TaskPriority.LOW ? "1"
                : priority == TaskPriority.UNASSIGNED ? "0"
                : "Error";
        return done + "|" + priorityStatus + "|" + description;
    }

    public abstract String save();

    protected void setPriority(String number) throws DukeException {
        switch (number) {
        case "0":
            this.priority = TaskPriority.UNASSIGNED;
            break;
        case "1":
            this.priority = TaskPriority.LOW;
            break;
        case "2":
            this.priority = TaskPriority.NORMAL;
            break;
        case "3":
            this.priority = TaskPriority.HIGH;
            break;
        default:
            throw new DukeException("Invalid priority level: " + number);
        }
    }


    /**
     * Return the task by reading the detail of the task from the file
     *
     * @param taskString the description of the task from the file
     * @return the task fitting the description
     */
    public static Task fromString(String taskString) throws DukeException {
        String[] parts = taskString.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String priorityNumber = parts[2];

        assert type.equals("T") || type.equals("D") || type.equals("E") : "Invalid task type: " + type;
        switch (type) {
        case "T":
            Task task = new ToDo(parts[3]);
            if (isDone) {
                task.markAsDone();
            }
            task.setPriority(priorityNumber);
            return task;
        case "D":
            String byString = parts[4].trim(); // Extract deadline
            if (byString.contains(" ")) {
                Task deadline = getTask(byString, parts);
                if (isDone) {
                    deadline.markAsDone();
                }
                deadline.setPriority(priorityNumber);
                return deadline;

            } else {
                LocalDate byDate = LocalDate.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Task deadline = new Deadline(parts[3], byDate, null);
                if (isDone) {
                    deadline.markAsDone();
                }
                deadline.setPriority(priorityNumber);
                return deadline;
            }

        case "E":
            String fromString = parts[4]; // Extract start date
            Task event = getEvent(fromString, parts);
            if (isDone) {
                event.markAsDone();
            }
            event.setPriority(priorityNumber);
            return event;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    private static Task getEvent(String fromString, String[] parts) {
        LocalDate fromDate = LocalDate.parse(fromString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String toString = parts[5]; // Extract end date
        LocalDate toDate = LocalDate.parse(toString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task event = new Events(parts[3], fromDate, toDate);
        return event;
    }

    private static Task getTask(String byString, String[] parts) {
        String[] part = byString.split(" ");
        String dateString = part[0];
        String timeString = part[1];
        LocalDate d1;
        LocalTime t1;

        d1 = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        t1 = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
        Task deadline = new Deadline(parts[3], d1, t1);
        return deadline;
    }
}
