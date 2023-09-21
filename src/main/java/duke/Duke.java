package duke;

import duke.command.AddTagCommand;
import duke.command.AddTaskCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.FindTagCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UntagCommand;
import duke.data.task.builder.DeadlineBuilder;
import duke.data.task.builder.EventBuilder;
import duke.data.task.builder.TodoBuilder;
import duke.exception.DukeException;
import duke.ui.Invoker;

/**
 * The main class of the application that interacts with the user.
 */
public class Duke {
    private static final String FINISH_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String WELCOME_MESSAGE = "Hello! I'm Doctor101\nWhat can I do for you?";
    private final Invoker invoker;
    /**
     * Constructor for Duke.Initializes the invoker with commands.
     */
    public Duke() {
        this.invoker = new Invoker();
        invoker.setCommand("list", new ListCommand());
        invoker.setCommand("mark", new MarkCommand());
        invoker.setCommand("unmark", new UnmarkCommand());
        invoker.setCommand("todo", new AddTaskCommand(new TodoBuilder()));
        invoker.setCommand("deadline", new AddTaskCommand(new DeadlineBuilder()));
        invoker.setCommand("event", new AddTaskCommand(new EventBuilder()));
        invoker.setCommand("delete", new DeleteCommand());
        invoker.setCommand("find", new FindCommand());
        invoker.setCommand("tag", new AddTagCommand());
        invoker.setCommand("untag", new UntagCommand());
        invoker.setCommand("findtag", new FindTagCommand());
    }
    public static String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }
    public static String getFinishMessage() {
        return FINISH_MESSAGE;
    }
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return FINISH_MESSAGE;
        } else {
            try {
                return invoker.execute(input);
            } catch (DukeException e) {
                return "error: " + e.getMessage();
            }
        }
    }
}
