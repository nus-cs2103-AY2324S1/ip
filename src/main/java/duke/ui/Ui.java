package duke.ui;

import duke.exception.ChatException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    public String formatTaskResponse(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
    public String listTaskResponse(TaskList tasks) throws ChatException {
        String output = "Here are the tasks in your list:";
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            output = output.concat(System.lineSeparator() + i + ". " + tasks.getTask(i).toString());
        }
        return output;
    }
    public String listMatchingTaskResponse(TaskList tasks) throws ChatException {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 1; i < tasks.getSize() + 1; i++) {
            output = output.concat(System.lineSeparator() + i + ". " + tasks.getTask(i).toString());
        }
        return output;
    }
    public String deleteTaskResponse(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.getSize() + " tasks in the list.";
    }
    public String markDoneResponse(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }
    public String markUndoneResponse(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
    public String exitResponse() {
        return "Bye. Hope to see you again soon!";
    }
    public String showLoadingError(ChatException e) {
        return e.getMessage();
    }
    public String prompt() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n"
                + "I can understand the following commands: \n"
                + "1. todo description \n"
                + "2. deadline description /by date (yyyy-mm-dd)\n"
                + "3. event description /from time /to time\n"
                + "4. list\n"
                + "5. mark task number\n"
                + "6. unmark task number\n"
                + "7. delete task number\n"
                + "8. bye";
    }
}
