package Utils;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    private TodoList todo;
    private String input;
    private boolean active;

    public Input() {
        this.active = true;
        this.todo = new TodoList();
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
        else if (command.equals("mark")) {
            return this.todo.mark(Integer.parseInt(commandArgs[1]));
        }
        else if (command.equals("unmark")) {
            return this.todo.unmark(Integer.parseInt(commandArgs[1]));
        }
        else if (command.equals("list")) {
            return this.todo.list();
        }

        return this.todo.add(input);
    }

    public boolean isActive() {
        return this.active;
    }
}


