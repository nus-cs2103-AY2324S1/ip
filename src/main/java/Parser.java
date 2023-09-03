import java.util.Scanner;

/**
 * This class parses and runs commands for various inputs.
 */
public class Parser {

    String[] input;
    String fullLine;

    Parser(String input) {
        this.fullLine = input;
        this.input = input.split(" ");
    }

    public String command() {
        return this.input[0];
    }
    public int number() {
        return Integer.parseInt(this.input[1]);
    }

    public int num() {
        return this.number() - 1;
    }

    public Deadline parseDeadline() throws DukeException {
        if (fullLine.split("/by ").length < 1) {
            new Ui().deadlineErrorPrinter();
            throw new DukeException(new Ui().deadlineErrorString());
        }
        String longName = fullLine.split("/by ")[0];
        String date = fullLine.split("/by ")[1];
        String taskName = longName.split("deadline ")[1];
        String deadlineName = taskName +
                String.format("(by: %s)", date);
        Deadline tempDeadline = new Deadline(deadlineName);
        return tempDeadline;
    }



}
