package duke;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

public class Parser {

    /**
     * Select command to run.
     * @param input The user's input.
     * @param taskList The list of tasks.
     */
    public static void parseCommand(String input, TaskList taskList) {
        try {
            if (input.equals("list")) {
                taskList.listTasks();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                taskList.markAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                taskList.markAsUndone(index);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                taskList.addTask(input);
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                taskList.deleteTask(index);
            }
            else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            Ui.showDottedLine();
            System.out.println(e.getMessage());
            Ui.showDottedLine();
        }
    }
}
