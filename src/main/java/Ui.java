import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public static void showLine() {
        System.out.println("-------------------------------");
    }
    public String getUserInput() {
        return scanner.nextLine();
    }

    public void welcomeMessage() {
        showLine();
        System.out.println("Hello! I'm Gideon");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void createFileMessage() {
        System.out.println("File created: Gideon");
    }
    public void openFileMessage() {
        System.out.println("Opening saved file.");
    }

    public void emptyTaskListMessage() {
        System.out.println("There are no tasks.");
    }

    public void showTasks(TaskList taskList) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.toString());
        showLine();
    }

    public void markedMessage(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        showLine();
    }

    public void unmarkedMessage(Task task) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        showLine();
    }

    public void addTaskMessage(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void deleteTaskMessage(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void invalidTaskMessage() {
        showLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        showLine();
    }
}
