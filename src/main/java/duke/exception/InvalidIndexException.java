package duke.exception;

import duke.Ui;

public class InvalidIndexException extends Exception {

    public InvalidIndexException() {
    }

    @Override
    public String toString() {

        String result = "\n\tOOPS! Please choose a proper index.";
        return Ui.showLine() + result + "\n" + Ui.showLine();
    }
}
