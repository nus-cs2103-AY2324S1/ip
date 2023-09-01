package duck;

import java.util.Scanner;

import duck.task.Task;
import duck.task.TaskList;

public class Ui {
    Scanner scanner = new Scanner(System.in);
    
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcomeMessage() {
        String greeting = "Quack! I'm Duck!\n" +
                "What can I do for you?";

        showLine();
        System.out.println(greeting);
        showLine();
    }

    public void showExitMessage() {
        String bye = "Quack! Hope to see you again soon!";

        System.out.println(bye);
        scanner.close();
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void showAddTaskMessage(Task task, int taskCount) {
        String addTask = "Got it. I've added this task:\n" + 
                task + "\n" + 
                "Now you have " + taskCount + " task(s) in the list.";

        System.out.println(addTask);
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.getTaskCount() == 0) {
            System.out.println("You have no tasks yet.");
            return;
        }
        String allTasks = "Here are the tasks in your list:\n" + 
                tasks;

        System.out.println(allTasks);
    }

    public void showMarkTaskMessage(Task task) {
        String markTask = "Nice! I've marked this task as done:\n" + 
                task;

        System.out.println(markTask);
    }

    public void showUnmarkTaskMessage(Task task) {
        String unmarkTask = "OK, I've unmarked this task:\n" + 
                task;

        System.out.println(unmarkTask);
    }

    public void showDeleteTaskMessage(Task task, int taskCount) {
        String deleteTask = "Noted. I've removed this task:\n" + 
                task + "\n" + 
                "Now you have " + taskCount + " task(s) in the list.";

        System.out.println(deleteTask);
    }

    public void showErrorMessage(DuckException e) {
        String error = e.getMessage();

        System.out.println(error);
    }
}
