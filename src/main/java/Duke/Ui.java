package Duke;

import DukeException.DukeException;
import Task.TaskList;

public class Ui {

    private Duke duke;
    private InputParser inputParser;

    public Ui(Duke duke, TaskList taskList) {
        this.duke = duke;
        inputParser = new InputParser(taskList);
    }
    public void HandleLine(String input) {
        ShowMessage(inputParser.HandleInput(input, duke));
    }
    public void ShowMessage(Message message) {
        message.Print();
    }

    public void ShowError(DukeException e) {

    }
}
