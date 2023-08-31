package Utils;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Input {
    private static final Set<String> commands = Set.of(
        "mark",
        "unmark",
        "list",
        "todo",
        "deadline",
        "event"
      );
    private static Scanner scanner = new Scanner(System.in);
    private TaskList tasks;
    private String input;
    private boolean active;

    public Input() {
        this.active = true;
        this.tasks = new TaskList();
    }

    public void readCommand() {
        this.input = Input.scanner.nextLine();
    }

    public Response executeCommand() {
        String[] commandArgs = this.input.split(" ");
        String command = commandArgs[0];
        if (command.equals("bye")) {
            this.active = false;
            return new TerminateResponse();
        }
        else if (commands.contains(command)) {
            return this.tasks.execute(this.input,commandArgs);
        }
        return new InputResponse("Error");
    }

    public boolean isActive() {
        return this.active;
    }
}


