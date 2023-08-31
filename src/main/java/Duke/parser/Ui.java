package Duke.parser;

import Duke.command.Command;
import Duke.exception.DukeException;
import Duke.message.Message;

import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    private final InputParser parser = new InputParser();
    public Command parseLine() throws DukeException {
        return parser.parseInput(scanner.nextLine());
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }
    public void showMessage(Message message) {
        message.print();
    }

    public void showError(DukeException e) {
        e.generateErrorMessage(e.getMessage()).print();
    }
}
