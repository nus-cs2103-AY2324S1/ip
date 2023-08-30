import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadlines extends Tasks {

    private String taskDesc;
    private String dueDateStr;
    private LocalDateTime dueDate;
    public Deadlines(String userInput) {
        try {
            String[] split = userInput.split("/by");
            if (userInput.equals("deadline")) {
                throw new IllegalArgumentException("Hey! Please enter the task description or leave a space after the command!");
            } else if (split.length < 2) {
                throw new IllegalArgumentException("Hey! Please provide a deadline for your task in this format dd/MM/yyyy HHmm!");
            } else {
                DateTime dateTime = new DateTime();
                String formattedDate = dateTime.formatDateTime(split[1].trim());
                this.taskDesc = split[0].trim().substring(8).trim();

                if (formattedDate.equals("Invalid format")) {
                    throw new IllegalArgumentException("Hey! Please provide a deadline for your task in this format dd/MM/yyyy HHmm!");
                } else {
                    this.dueDateStr = formattedDate;
                    this.dueDate = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.taskDesc = null;
        }
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

}
