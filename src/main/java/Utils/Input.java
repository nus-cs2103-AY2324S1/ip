package Utils;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    private String input;
    private boolean active;

    public Input() {
        this.active = true;
    }

    public void readCommand() {
        this.input = Input.scanner.nextLine();
    }

    public Response executeCommand() {
        if (this.input.equals("bye")) {
            this.active = false;
            return new TerminateResponse();
        }
        return new InputResponse(this.input);
    }

    public boolean isActive() {
        return this.active;
    }
}


