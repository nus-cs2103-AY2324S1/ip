import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleCommand extends Command {
    private String dateInput;

    public ScheduleCommand(String dateInput) {
        this.dateInput = dateInput;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task dateUtility = new Task("");
        LocalDateTime dateToShow = dateUtility.stringToDate(dateInput);
        List<Task> tasksOnDate = actionList.tasksOnDate(dateToShow);
        if (tasksOnDate.isEmpty()) {
            ui.lineSandwich(" No tasks found on " + dateUtility.dateToString(dateToShow) + ".");
        } else {
            StringBuilder tasksMessage = new StringBuilder();
            for (int i = 0; i < tasksOnDate.size(); i++) {
                tasksMessage.append(" ").append(i + 1).append(". ").append(tasksOnDate.get(i)).append("\n");
            }
            ui.lineSandwich(" Tasks on " + dateUtility.dateToString(dateToShow) + ":\n" + tasksMessage);
        }
    }
}
