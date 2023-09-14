package taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import taskutility.TaskMatcher;

/**
 * An Event task type
 */
public class Event extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    private String taskDesc; //task description
    private String fromDateStr; //start date and time in String
    private String toDateStr; //end date and time in String
    private LocalDateTime fromDate; //start date and time in LocalDateTime
    private LocalDateTime toDate; // end date and time in LocalDateTime

    /**
     * Constructs a new Event task with the specified user input.
     *
     * @param taskDesc The task description.
     * @param fromDateStr      The starting date and time of the event.
     * @param toDateStr        The ending date and time of the event.
     */
    public Event(String taskDesc, String fromDateStr, String toDateStr) {
        assert taskDesc != null : "taskDesc must not be null";
        assert fromDateStr != null : "fromDateStr must not be null";
        assert toDateStr != null : "toDateStr must not be null";

        this.taskDesc = taskDesc;
        this.fromDateStr = fromDateStr;
        this.toDateStr = toDateStr;
        this.fromDate = LocalDateTime.parse(fromDateStr, DATE_TIME_FORMATTER);
        this.toDate = LocalDateTime.parse(toDateStr, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param completion The completion status (1 for done, 0 for not done).
     * @param taskDesc   The task description.
     * @param fromDateStr       The starting date and time of the event.
     * @param toDateStr         The ending date and time of the event.
     */
    public Event(String completion, String taskDesc, String fromDateStr, String toDateStr) {
        assert taskDesc != null : "taskDesc must not be null";
        assert fromDateStr != null : "fromDateStr must not be null";
        assert toDateStr != null : "toDateStr must not be null";

        try {
            if (completion.equals("1")) {
                this.taskDesc = taskDesc.trim();
                this.fromDateStr = fromDateStr.trim();
                this.toDateStr = toDateStr.trim();
                this.fromDate = LocalDateTime.parse(fromDateStr, DATE_TIME_FORMATTER);
                this.toDate = LocalDateTime.parse(toDateStr, DATE_TIME_FORMATTER);
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.fromDateStr = fromDateStr.trim();
                this.toDateStr = toDateStr.trim();
                this.fromDate = LocalDateTime.parse(fromDateStr, DATE_TIME_FORMATTER);
                this.toDate = LocalDateTime.parse(toDateStr, DATE_TIME_FORMATTER);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid event task in the task list!");
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

        if (this.isDone) {
            x = "1";
        } else {
            x = "0";
        }

        String str1 = String.format("%s", x);

        return "E | " + str1 + " | " + this.taskDesc + " | " + this.fromDateStr + " | " + this.toDateStr;
    }

    /**
     * Generates a string representation of the Events task.
     *
     * @return A formatted string representation for displaying to the user.
     */
    @Override
    public String toString() {
        String x;

        if (this.isDone) {
            x = "X";
        } else {
            x = " ";
        }

        String str1 = String.format("[%s] ", x);
        String str2 = String.format(" (from: %s to: %s)", this.fromDateStr, this.toDateStr);

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
        Event event = (Event) o;

        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!taskDesc.equals(event.taskDesc)) {
            return false;
        } else if (!fromDateStr.equals(event.fromDateStr)) {
            return false;
        } else {
            return toDateStr.equals(event.toDateStr);
        }
    }

    /**
     * Matches the taskDesc with keyword for query.
     *
     * @param keyword The keyword for matching.
     * @return True if there is matching keyword; otherwise, false.
     */
    public boolean isMatch(String keyword) {
        return TaskMatcher.isMatch(keyword, taskDesc);
    }
}
