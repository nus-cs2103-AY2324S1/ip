package TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadlines extends Tasks {

    private String taskDesc; //task description
    private String dueDateStr; // due date in String
    private LocalDateTime dueDate; // due date in LocalDateTime

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param taskDesc  The task description.
     * @param dueDateStr The due date and time of the event.
     */
    public Deadlines(String taskDesc, String dueDateStr) {
        this.taskDesc = taskDesc;
        this.dueDateStr = dueDateStr;
        this.dueDate = LocalDateTime.parse(dueDateStr, DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"));
    }

    /**
     * Constructs a new Event task with the specified description, completion status, and date-time information.
     *
     * @param completion The completion status (1 for done, 0 for not done).
     * @param taskDesc   The task description.
     * @param duedate    The due date and time of the event.
     */
    public Deadlines(String completion, String taskDesc, String duedate) {
        try {
            if (completion.equals("1")){
                this.taskDesc = taskDesc.trim();
                this.dueDateStr = duedate.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.dueDateStr = duedate.trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid todo task in the task list!");
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
        if (this.status) {
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
        if (this.status) {
            x = "X";
        } else {
            x = " ";
        }
        String str1 = String.format("[%s] ", x);
        String str2 = String.format(" (by: %s)", this.dueDateStr);
        return "[D]" + str1 + this.taskDesc + str2 ;
    }

    /**
     * Compares this Deadlines task to another object for equality.
     *
     * @param o The object for comparison.
     * @return  True if the objects are equal; otherwise, false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deadlines deadlines = (Deadlines) o;

        if (!taskDesc.equals(deadlines.taskDesc)) return false;
        return dueDateStr.equals(deadlines.dueDateStr);
    }

}
