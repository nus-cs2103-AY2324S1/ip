package command;

import task.TaskList;
import task.ToDo;
import ui.Reply;

import java.util.Scanner;

public class ToDoCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        reply.printDialog("So you want to add a ToDo task. Tell me what's the task.");
        String desc = scanner.nextLine();
        tasks.addTask(new ToDo(desc));
    }
}
