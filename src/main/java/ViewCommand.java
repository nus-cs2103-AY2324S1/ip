import java.time.format.DateTimeParseException;

public class ViewCommand extends Command{
    private String command;
    private View viewType;

    public ViewCommand(String command, View viewType) {
        this.command = command;
        this.viewType = viewType;
    }

    @Override
    public void execute() {
        switch(this.viewType) {
        case LISTALL:
            TaskList.printAllContent();
            break;

        case LISTONEDAY:
            try {
                TaskList.printTaskForDate(command);
            } catch (DateTimeParseException e) {
                Ui.printAlertForDate();
            } finally {
                break;
            }
        }
    }

}
