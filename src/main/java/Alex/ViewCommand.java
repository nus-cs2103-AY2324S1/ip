package Alex;

import java.time.format.DateTimeParseException;

/**
 * A class that extends from Commadn class. The instance of this class represents a command that is trying to
 * view the existing the class in the TaskList. It includes listing all the task(s) in the TaskList, listing all the
 * task(s) in a specific date.
 */
public class ViewCommand extends Command {
    private String command;
    private View viewType;

    /**
     * The constructor of ViewCommand class when the user input string and viewType is given.
     *
     * @param command The user input string.
     * @param viewType The type of view command which consist of "LISTALL", "LISTONEDAY".
     */
    public ViewCommand(String command, View viewType) {
        this.command = command;
        this.viewType = viewType;
    }

    /**
     * Override the method from Command class.
     * The execute method of ViewCommand class is to actually print all the task(s) in the TaskList or
     * the task(s) of a specific date to the standard output.
     */
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
