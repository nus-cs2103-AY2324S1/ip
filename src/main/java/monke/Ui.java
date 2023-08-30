package monke;

import monke.tasks.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public void print(String msg) {
        System.out.println("\t" + msg);
    }

    public void showWelcome() {
        this.print("Hello, I'm Monke. OOGA BOOGA!");
        this.print("What can I do for you?");
        this.printHorizontalLine();
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String fullCommand = sc.nextLine();
            return fullCommand;
        }
        sc.close();
        return "";
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public void displayList(TaskList taskList) {
        List<Task> tasks = taskList.toList();
        for (int i = 0; i < tasks.size(); i++) {
            this.print((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showAddTask(Task task, int tasksSize) {
        this.print("Got it. I've added this task:");
        this.print("\t" + task);
        this.print("Now you have " + tasksSize + " tasks in the list.");
    }

    public void printExit() {
        this.print("Bye. Hope to see you again soon! OOGA BOOGA!");
    }

}
