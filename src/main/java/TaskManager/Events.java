package TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Tasks {

    private String taskDesc; //task description
    private String from; //start date and time in String
    private String to; //end date and time in String
    private LocalDateTime fromDate; //start date and time in LocalDateTime
    private LocalDateTime toDate; // end date and time in LocalDateTime

    /**
     * Constructs a new Event task with the specified user input.
     *
     * @param taskDesc The task description.
     * @param from      The starting date and time of the event.
     * @param to        The ending date and time of the event.
     */
    public Events(String taskDesc, String from, String to) {
        this.taskDesc = taskDesc;
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param completion The completion status (1 for done, 0 for not done).
     * @param taskDesc   The task description.
     * @param from       The starting date and time of the event.
     * @param to         The ending date and time of the event.
     */
    public Events(String completion, String taskDesc, String from, String to) {
        try {
            if (completion.equals("1")){
                this.taskDesc = taskDesc.trim();
                this.from = from.trim();
                this.to = to.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.from = from.trim();
                this.to = to.trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid todo task in the task list!");
            this.taskDesc = null;
        }
    }

    /**
     * Checks if the Events task is valid.
     *
     * @return True if the task is valid (not null); otherwise, false.
     */
    public boolean isValid() {
        return taskDesc != null;
    }

    /**
     * Returns a string format of the Events task that can be written to the .txt file.
     *
     * @return A string format of the Events task to store it into the .txt file.
     */
    @Override
    public String toFileString() {
        String x;
        if (this.status) {
            x = "1";
        } else {
            x = "0";
        }

        String str1 = String.format("%s", x);
        return "E | " + str1 + " | " + this.taskDesc + " | " + this.from + " | " + this.to;
    }

    /**
     * Generates a string representation of the Events task.
     *
     * @return A formatted string representation for displaying to the user.
     */
    @Override
    public String toString() {
        String x;
        if (this.status) {
            x = "X";
        } else {
            x = " ";
        }
        String str1 = String.format("[%s] ", x);
        String str2 = String.format(" (from: %s to: %s)", this.from, this.to);
        return "[E]" + str1 + this.taskDesc + str2;
    }

    /**
     * Compares this Events task to another object for equality.
     *
     * @param o The object for comparison.
     * @return  True if the objects are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        if (!taskDesc.equals(events.taskDesc)) return false;
        if (!from.equals(events.from)) return false;
        return to.equals(events.to);
    }



}
