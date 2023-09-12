package chatty.utils;

import chatty.exception.InvalidTaskNumberException;
import chatty.task.Task;
import chatty.task.TaskList;

import java.util.Scanner;
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }
    public String showGreet() {
        return "Hi there! I'm Chatty! \n How can I help you today?";
    }

    public String showExit() {
        return "Bye! Hope to see you again soon!";
    }

    public String showList(TaskList taskList) {
        if (taskList.listSize() == 0) {
            return "There is currently no chatty.task in your list.";
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 0; i < taskList.listSize(); i++) {
                result.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
            }
            return result.toString();
        }
    }

    public String showDone(int i, TaskList taskList) {
        return "Nice! I've marked this chatty.task as done: \n" + "         " + taskList.showTask(i);
    }

    public String showUndone(int i, TaskList taskList) {
        return "Ok, I've marked this chatty.task as not done: \n" + "           " + taskList.showTask(i);
    }

    public String showDelete(int i, TaskList taskList) throws InvalidTaskNumberException {
        return "Alright, I've removed this chatty.task from the list: \n"
                + "           " + taskList.deleteTask(i) + "\n"
                + "Now you have" + taskList.listSize() + " task(s) in your list.";
    }

    public String showAdded(Task task, TaskList taskList) {
        return "Got it. I've added this chatty.task into the list: \n"
                + "         " + task + "\n"
                + "You now have " + taskList.listSize() + " task(s) in the list.";
    }

    public String showInvalid() {
        return "Sorry, I don't understand this command";
    }

    public String showMatched(String matchedTask) {
        if (matchedTask.isEmpty()) {
            return "There is no tasks matching your keyword";
        } else {
            return "Here are the list of task(s) matching your keyword: \n" + matchedTask;
        }
    }
}
