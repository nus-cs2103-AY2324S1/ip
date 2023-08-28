import java.util.ArrayList;

public class Parser {

    public static void parse(String input, ArrayList<Task> taskList) {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
        case "list":
            TaskList.listTasks(taskList);
            break;
        case "mark":
            TaskList.markTask(inputParts, taskList, true);
            break;
        case "unmark":
            TaskList.markTask(inputParts, taskList, false);
            break;
        case "delete":
            TaskList.deleteTask(inputParts, taskList);
            break;
        default:
            TaskList.addTask(inputParts, taskList);
            break;
        }
    }

}
