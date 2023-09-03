package Alex;

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

        case FIND:
            try {
                if (command.length() == 4 || !command.substring(4, 5).equals(" ")) {
                    throw new AlexException("");
                }
                String toMatch = command.substring(5).stripTrailing();
                TaskList.printTaskForMatchWord(toMatch);
            } catch (AlexException e) {
                Ui.printAlertForFind();
            } finally {
                break;
            }
        }

    }

}
