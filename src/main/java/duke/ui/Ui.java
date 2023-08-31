package duke.ui;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;

public class Ui {
    private Scanner scanner;
    private static final String DIVIDER = "___________________________________\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void showExit() {
        System.out.println("Bye Bye. Hope to see you again soon!\n");
    }

    public void showWelcome(ArrayList<Task> list) {
        String msg = "Looks like you have been here before!\n";
        if (list.isEmpty()) {
            msg = "Looks like your list is empty!\nTime to add some new Tasks!\n";
        }
        String welcome = String.format("Hi I'm Duke but BETTTERRRR!!!\n%s", msg);
        System.out.println(welcome + DIVIDER);
        if (!list.isEmpty()){
            showList(list);
            showDivider();
        }
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showDelete(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay I have deleted this task from the list\n\t %s\n" +
                "Now you have %d items in your list\n", task.toString(), list.size());
        System.out.println(msg);
    }

    public void showTaskAdded(Task task, ArrayList<Task> list) {
        String msg = String.format("Okay!! I have added a new %s\n\t %s\n" +
                "You now have %d items in your list!", task.getType(), task.toString(), list.size());
        System.out.println(msg);
    }

    public void showMark(String type, Task task) {
        if (type.equals("mark")) {
            String msg = String.format("Nice!! I have marked this task as done:\n%s\n", task.toString());
        }
        String msg = String.format("Okay I have unmarked this task:\n%s\n", task.toString());
        System.out.println(msg);
    }

    public void showList(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + 1 + ". " + temp[i].toString());
        }
    }

    public void showListMatching(ArrayList<Task> list) {
        Task[] temp = list.toArray(new Task[0]);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < temp.length; i++) {
            System.out.println(i + 1 + ". " + temp[i].toString());
        }
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void invalidCommand() {
        System.out.println(DIVIDER + "Oops! That does not seem to be a valid action!\n" + DIVIDER);
    }
}
