package command;
import duke.TaskList;
import duke.UserInterface;

public class HelpCommand implements Commandable {
    /**
     * @param list
     * @param ui
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output("Here's the guidelines for all functions.\n"
                + "help: lists this help guide\n"
                + "list: lists all tasks\n"
                + "bye: ends bot\n"
                + "mark: mark (int x); marks indicated task\n"
                + "unmark: unmark (int x); unmarks indicated task\n"
                + "event: event (String name) /from (Date start) /to (Date end); creates event\n"
                + "todo: todo (String name); creates todo\n"
                + "deadline: deadline (String name) /by (Date deadline); creates deadline\n"
        );
        return false;
    }
}
