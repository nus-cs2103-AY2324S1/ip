import java.time.LocalDate;

public class Command {
    private final String commandType;
    private String description;
    private int taskIndex;
    private LocalDate deadlineDate;
    private LocalDate eventFromDate;
    private LocalDate eventToDate;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public Command(String commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    public Command(String commandType, int taskIndex) {
        this.commandType = commandType;
        this.taskIndex = taskIndex;
    }

    public Command(String commandType, String description, LocalDate deadlineDate) {
        this.commandType = commandType;
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    public Command(String commandType, String description, LocalDate eventFromDate, LocalDate eventToDate) {
        this.commandType = commandType;
        this.description = description;
        this.eventFromDate = eventFromDate;
        this.eventToDate = eventToDate;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }
    public String getCommandType() {
        return this.commandType;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    public LocalDate getEventFromDate() {
        return this.eventFromDate;
    }

    public LocalDate getEventToDate() {
        return this.eventToDate;
    }
}