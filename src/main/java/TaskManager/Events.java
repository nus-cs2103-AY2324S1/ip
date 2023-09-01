package TaskManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Events extends Tasks {

    private String taskDesc;
    private String from;
    private String to;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public Events(String userInput, String from, String to) {
        this.taskDesc = userInput;
        this.from = from;
        this.to = to;
    }

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
        return "E | " + str1 + " | " + this.taskDesc + " | " + this.from + " | " + this.to;
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
        String str2 = String.format(" (from: %s to: %s)", this.from, this.to);
        return "[E]" + str1 + this.taskDesc + str2;
    }

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
