package command;

import task.TaskList;
import task.ToDo;
import ui.Reply;

import java.util.Scanner;

/**
 * Class for Todo command with its static implementation of its processes
 */
public class ToDoCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of todo Command. The choice of keeping the commands static is because there is no need for multiple
     * instances of commands
     * Prompts user to enter the task name before adding it to the list.
     */
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        reply.printDialog("So you want to add a ToDo task. Tell me what's the task.");
        String desc = scanner.nextLine();
        tasks.addTask(new ToDo(desc));
    }
}
