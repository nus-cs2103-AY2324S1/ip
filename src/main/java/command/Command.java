package command;

import duke.TaskList;
import duke.Ui;

public interface Command {

    static String process(String input, TaskList tasks, Ui ui) {
        return null;
    }
}
