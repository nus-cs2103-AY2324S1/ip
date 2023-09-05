package Utils;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    private TaskList tasks;
    private String input;

    public Input() {
        this.tasks = new TaskList();
    }
    
    public Response command() {
        this.input = Input.scanner.nextLine();
        return executeCommand();
    }

    public Response executeCommand() {
        String command = this.input.split(" ")[0];
        if (command.equals("bye")) {
            return Response.TERMINATE;
        }
        try {
            return this.tasks.execute(this.input, command);
        } catch (DukeException e) {
            return Response.generate(e.toString());
        }
    }
}


