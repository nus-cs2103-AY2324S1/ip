package Duke.parser;

import Duke.exception.DukeException;
import Duke.message.Message;
import Duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    private final InputParser parser = new InputParser();
    private final TaskList taskList;
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }
    public void HandleLine() {
        if (scanner.hasNext()) {
            try {
                ShowMessage(parser.HandleInput(scanner.nextLine())
                        .execute(taskList));
            } catch (DukeException de) {
                ShowError(de);
            }
        }
    }
    public void ShowMessage(Message message) {
        message.Print();
    }

    public void ShowError(DukeException e) {
        e.generateErrorMessage(e.getMessage()).Print();
    }
}
