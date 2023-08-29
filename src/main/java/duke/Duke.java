package duke;

import java.util.ArrayList;

/**
 * Duke is a task management chatbot that allows users to manage their tasks
 * including todos, deadlines, and events.
 */
public class Duke {
    /**
     * The main method that initializes and runs the Duke application.
     *
     * @param args The command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("src/main/java/duke/data/duke.txt");
        TaskList taskList = new TaskList();

        Ui.showGreeting();

        ArrayList<Task> loadedTasks = storage.loadTasks();
        taskList.setTasks(loadedTasks);

        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equalsIgnoreCase("bye")) {
                storage.saveTasks(taskList.getTasks());
                Ui.showByeMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                Ui.showList(taskList);
            } else {
                Parser.parseAndAddTask(userInput, taskList);
            }
        }
    }
}
