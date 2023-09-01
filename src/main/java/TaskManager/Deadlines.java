package TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Deadlines extends Tasks {

    private String taskDesc;
    private String dueDateStr;
    private LocalDateTime dueDate;
    public Deadlines(String userInput, String dueDateStr) {
        this.taskDesc = userInput;
        this.dueDateStr = dueDateStr;
        this.dueDate = LocalDateTime.parse(dueDateStr, DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"));
    }

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

    public boolean isValid() {
        return taskDesc != null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deadlines deadlines = (Deadlines) o;

        if (!taskDesc.equals(deadlines.taskDesc)) return false;
        return dueDateStr.equals(deadlines.dueDateStr);
    }

}
