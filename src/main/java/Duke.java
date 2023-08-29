import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("src/main/java/data/duke.txt");
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
