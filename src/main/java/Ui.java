import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println(
                "___________________________________________________________\n"
                        + "Hello! I'm Fishron\n"
                        + "What can I do for you?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("___________________________________________________________");
    }

    public String readCommand() {
        String userCommand = sc.nextLine();
        return userCommand;
    }

    public void showErrorMessage(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showTaskAdded(TaskList taskList) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getList().get(taskList.getSize() - 1).toString());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> list = taskList.getList();
        int start = 1;
        for (Task listItems : list) {
            System.out.println(start + ". " + listItems.toString());
            start++;
        }
    }

    public void showTaskDeleted(TaskList tasklist, Task deleted) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deleted);
        System.out.println("Now you have " + tasklist.getSize() + " tasks in the list.");
    }

    public void showDoneTask(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("I've marked this task as undone:");
        System.out.println("  " + task.toString());
    }

}