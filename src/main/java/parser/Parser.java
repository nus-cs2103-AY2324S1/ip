package parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import command.Commands;
import dukeException.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import taskList.TaskList;

/**
 * deals with making sense of the user command
 */
public class Parser {
    public Parser() {

    }
    Pattern listRegex = Pattern.compile("^" + Commands.LIST, Pattern.CASE_INSENSITIVE);
    Pattern markRegex = Pattern.compile("^" + Commands.MARK, Pattern.CASE_INSENSITIVE);
    Pattern unmarkRegex = Pattern.compile("^" + Commands.UNMARK, Pattern.CASE_INSENSITIVE);
    Pattern deadlineRegex = Pattern.compile("^" + Commands.DEADLINE, Pattern.CASE_INSENSITIVE);
    Pattern todoRegex = Pattern.compile("^" + Commands.TODO, Pattern.CASE_INSENSITIVE);
    Pattern eventRegex = Pattern.compile("^" + Commands.EVENT, Pattern.CASE_INSENSITIVE);
    Pattern deleteRegex = Pattern.compile("^" + Commands.DELETE, Pattern.CASE_INSENSITIVE);

    public int parseCommand(String command, TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        if (command.equals("bye")) {
            return 1;
        } else if (listRegex.matcher(command).find()) {
            Task.printList(list);
            return 0;
        } else if (markRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(5)) - 1;
            Task currTask = list.get(curr);
            currTask.markDone();
            return 0;
        } else if (unmarkRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(7)) - 1;
            Task currTask = list.get(curr);
            currTask.markUndone();
            return 0;
        } else if (deadlineRegex.matcher(command).find()) {
            Deadline.addDeadline(list, command);
            return 0;
        } else if (todoRegex.matcher(command).find()) {
            Todo.addTodo(list, command);
            return 0;
        } else if (eventRegex.matcher(command).find()) {
            Event.addEvent(list, command);
            return 0;
        } else if (deleteRegex.matcher(command).find()) {
            Task.deleteTask(list, command);
            return 0;
        } else {
            try {
                throw new DukeException("Invalid Response");
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return 0;
            }
        }
    }
}
