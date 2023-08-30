package seedu;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner sc;
    private boolean on;
    private final PrintStream out;
    public Ui() {
        this.sc = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcome() {
        String logo = "Bacon Pancake";
        out.println(" Hello from " + logo + "\n What can I do for you? \n" + "---------------------------------- \n");
    }

    public boolean showByeMessage() {
        out.println("Bacon Pancake : \n" + "Bye! See you again soon ");
        return false;
    }

    public void showTask(TaskList tasks) {
        out.println("Bacon Pancake : Below are the lists\n");
        int curr = 1;
        for (int i = 0; i < tasks.getLen(); i++) {
            if (tasks.get(i) != null) {
                Task t = tasks.get(i);
                out.println((curr++) + ". " + t.getStatus());
            }
        }
    }

    public void showError(String m) {
        out.println(m);
    }

    public void showLine() {
        out.println("----------------------------------");
    }

    public String getUserCommand() {
        out.println("Enter your input : ");
        return sc.nextLine();
    }

    public void removeTask(Task t, int currentLength) {
        out.println("Noted. I've removed this task: \n");
        out.println(t);
        out.println("Now you have " + currentLength + " tasks left.");
    }

    public void showMarked() {
        out.println("Bacon Pancake : Nice! I've marked this task as done:");
    }

    public void addTask(Task curr) {
        out.println("Bacon Pancake : \n Added : " + curr.description);
    }

    public void showFoundWords(ArrayList<Task> tasks) {
        for(Task task : tasks) {
            System.out.println(task.getStatus());
        }
    }

}
