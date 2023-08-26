import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FilterCommand extends Command {

    private final LocalDate date;

    public FilterCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {

        int deadlineIndex = 0;
        StringBuilder deadlinesOnDate = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isDueOn(this.date)) {
                deadlineIndex++;
                deadlinesOnDate.append(deadlineIndex).append(". ").append(task).append("\n");
            }
        }

        int eventIndex = 0;
        StringBuilder eventsHappeningOnDate = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Event && ((Event) task).isHappeningOn(this.date)) {
                eventIndex++;
                eventsHappeningOnDate.append(eventIndex).append(". ").append(task).append("\n");
            }
        }

        if (deadlineIndex == 0 && eventIndex == 0) {
            ui.showMessage("There are no tasks that are due and happening on "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
            return;
        }

        String output = "";

        if (deadlineIndex > 0) {
            output += (deadlineIndex == 1 ? "This is the task that is" : "Here are the tasks that are")
                    + " due on " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n";
        }
        output += deadlinesOnDate.toString();

        if (eventIndex > 0) {
            output += (eventIndex == 1 ? "This is the task that is" : "Here are the tasks that are")
                    + " happening on " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n";
        }
        output += eventsHappeningOnDate.toString();
        ui.showMessage(output);
    }
}
