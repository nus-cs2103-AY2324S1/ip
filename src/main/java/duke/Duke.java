package duke;

import java.util.Scanner;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.data.task.builder.DeadlineBuilder;
import duke.data.task.builder.EventBuilder;
import duke.data.task.builder.TodoBuilder;
import duke.command.AddTaskCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.ui.Invoker;

public class Duke {
    private Invoker invoker = new Invoker();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Doctor101");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Duke duke = new Duke();
        duke.invoker.setCommand("list", new ListCommand());
        duke.invoker.setCommand("mark", new MarkCommand());
        duke.invoker.setCommand("unmark", new UnmarkCommand());
        duke.invoker.setCommand("todo", new AddTaskCommand(new TodoBuilder()));
        duke.invoker.setCommand("deadline", new AddTaskCommand(new DeadlineBuilder()));
        duke.invoker.setCommand("event", new AddTaskCommand(new EventBuilder()));
        duke.invoker.setCommand("delete", new DeleteCommand());
        duke.invoker.setCommand("find", new FindCommand());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                scanner.close();
                return;
            }
            try {
                duke.invoker.execute(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
    }

    public void init() {
        this.invoker.setCommand("list", new ListCommand());
        this.invoker.setCommand("mark", new MarkCommand());
        this.invoker.setCommand("unmark", new UnmarkCommand());
        this.invoker.setCommand("todo", new AddTaskCommand(new TodoBuilder()));
        this.invoker.setCommand("deadline", new AddTaskCommand(new DeadlineBuilder()));
        this.invoker.setCommand("event", new AddTaskCommand(new EventBuilder()));
        this.invoker.setCommand("delete", new DeleteCommand());
        this.invoker.setCommand("find", new FindCommand());
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Doctor101\nWhat can I do for you?";
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else {
            try {
                return invoker.execute(input);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}