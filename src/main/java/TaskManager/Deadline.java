package taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import taskutility.TaskMatcher;

/**
 * A Deadline task type
 */
public class Deadline extends Task {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    private String taskDesc; //task description
    private String dueDateStr; // due date in String
    private LocalDateTime dueDate; // due date in LocalDateTime

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param taskDesc  The task description.
     * @param dueDateStr The due date and time of the event.
     */
    public Deadline(String taskDesc, String dueDateStr) {
        assert taskDesc != null : "taskDesc must not be null";
        assert dueDateStr != null : "dueDateStr must not be null";

        this.taskDesc = taskDesc;
        this.dueDateStr = dueDateStr;
        this.dueDate = LocalDateTime.parse(dueDateStr, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param completion The completion status (1 for done, 0 for not done).
     * @param taskDesc   The task description.
     * @param dueDateStr    The due date and time of the event.
     */
    public Deadline(String completion, String taskDesc, String dueDateStr) {
        assert completion != null : "completion must not be null";
        assert taskDesc != null : "taskDesc must not be null";
        assert dueDateStr != null : "dueDateStr must not be null";

        try {
            if (completion.equals("1")) {
                this.taskDesc = taskDesc.trim();
                this.dueDateStr = dueDateStr.trim();
                this.dueDate = LocalDateTime.parse(dueDateStr, DATE_TIME_FORMATTER);
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.dueDateStr = dueDateStr.trim();
                this.dueDate = LocalDateTime.parse(dueDateStr, DATE_TIME_FORMATTER);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid deadline task in the task list!");
            this.taskDesc = null;
        }
    }

    /**
     * Checks if the Deadlines task is valid.
     *
     * @return True if the task is valid (not null); otherwise, false.
     */
    public boolean isValid() {
        return taskDesc != null;
    }

    /**
     * Returns a string format of the Deadlines task that can be written to the .txt file.
     *
     * @return A string format of the Deadlines task to store it into the .txt file.
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

        return "D | " + str1 + " | " + this.taskDesc + " | " + this.dueDateStr;
    }

    /**
     * Generates a string representation of the Deadlines task.
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
        String str2 = String.format(" (by: %s)", this.dueDateStr);

        return "[D]" + str1 + this.taskDesc + str2;
    }

    /**
     * Compares this Deadlines task to another object for equality.
     *
     * @param o The object for comparison.
     * @return  True if the objects are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object o) {
        Deadline deadline = (Deadline) o;

        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else if (!taskDesc.equals(deadline.taskDesc)) {
            return false;
        } else {
            return dueDateStr.equals(deadline.dueDateStr);
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
