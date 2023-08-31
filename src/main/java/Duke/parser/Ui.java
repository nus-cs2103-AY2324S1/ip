package Duke.parser;

import Duke.command.Command;
import Duke.exception.DukeException;
import Duke.message.Message;

import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    private final InputParser parser = new InputParser();
    public Command ParseLine() throws DukeException {
        return parser.ParseInput(scanner.nextLine());
    }

    public boolean HasNext() {
        return scanner.hasNext();
    }
    public void ShowMessage(Message message) {
        message.Print();
    }

    public void ShowError(DukeException e) {
        e.generateErrorMessage(e.getMessage()).Print();
    }
}
