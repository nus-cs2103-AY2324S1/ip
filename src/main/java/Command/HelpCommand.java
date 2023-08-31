package Command;
import Duke.Duke;

public class HelpCommand implements Commandable {
    public void execute(Duke caller) {
        System.out.println("Here's the guidelines for all functions.\n" +
                "list: lists all tasks\n" +
                "bye: ends bot\n" +
                "mark: mark (int x); marks indicated task\n" +
                "unmark: unmark (int x); unmarks indicated task\n" +
                "event: event (String name) /from (String start) /to (String end); creates event\n" +
                "todo: todo (String name); creates todo\n" +
                "deadline: deadline (String name) /by (String deadline); creates deadline\n"
        );
    }
}
