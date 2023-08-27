import java.util.ArrayList;

public class Ui {
    public void showLoadingError() {
        System.out.println("~~No stored tasks detected~~\n");
    }

    public void greetMessage() {
        System.out.println("Hello! I'm HAPPY\nWhat can I do for you?\n");
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listMessage(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("  %d.%s%n", i + 1, task);
        }
    }

    public void markTaskAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  %s%n", task);
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("  %s%n", task);
    }

    public void deleteTaskMessage(Task task, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", numOfTasks);
    }

    public void addTaskMessage(Task task, int numOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", numOfTasks);
    }
}
